package org.fontys.course.registration.model;

import org.fontys.course.registration.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;

@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UtilService utilservice;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/courses").access("hasRole('ROLE_" + utilservice.admin + "')")
		.antMatchers("/login/doAdminLogin").access("hasRole('ROLE_" + utilservice.admin + "')")
		.antMatchers("/login/doStudentLogin").access("hasRole('ROLE_" + utilservice.student + "')")
		.antMatchers("/login/doTeacherLogin").access("hasRole('ROLE_" + utilservice.teacher + "')")
		.anyRequest()
		.permitAll()
		.and()
		.logout()
		.logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
		.deleteCookies("JSESSIONID")
		.permitAll()
		.and()
		.httpBasic()
		.and()
		.csrf()
		.disable()
		.cors();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private UtilService utilservice;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {
		
		@Override
		public UserDetails loadUserByUsername(String pcn) {
			// TODO Auto-generated method stub
			Person person = null;
			String role = null;
			Student student = utilservice.GetStudentById(pcn);
			if(student != null){
				role = utilservice.student;
				person = student;
			}
			
			Teacher teacher = utilservice.GetTeacherById(pcn);
			if(teacher != null){
				role = utilservice.teacher;
				person = teacher;
			}
			
			Admin admin = utilservice.GetAdminById(pcn);
			if(admin != null){
				role = utilservice.admin;
				person = admin;
			}
			return new User(person.getPcn().toString(), person.getPassword(), true, true, true, true,
	                AuthorityUtils.createAuthorityList("ROLE_" + role));
		}
	};
  }
}