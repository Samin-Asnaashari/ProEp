package org.fontys.course.registration.controller;

import javax.servlet.http.HttpServletRequest;

import org.fontys.course.registration.exception.Message;
import org.fontys.course.registration.service.LoginService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/login")
@Api(
        name = "Login model API",
        description = "provides the list of methods that manages the login function.",
        stage = ApiStage.RC
)
public class LoginController {
	
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/doAdminLogin", method = RequestMethod.GET)
    public Message AdminLogin(HttpServletRequest request) {
    	return new Message(request.getHeader("Authorization"));
    }
    
    @RequestMapping(value = "/doStudentLogin", method = RequestMethod.GET)
    public Message StudentLogin(HttpServletRequest request) {
    	return new Message(request.getHeader("Authorization"));
    }
    
    @RequestMapping(value = "/doTeacherLogin", method = RequestMethod.GET)
    public Message TeacherLogin(HttpServletRequest request) {
    	return new Message(request.getHeader("Authorization"));
    }
}
