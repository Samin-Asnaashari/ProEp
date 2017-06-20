package org.fontys.course.registration.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(  
	    use = JsonTypeInfo.Id.NAME,  
	    include = JsonTypeInfo.As.PROPERTY,  
	    property = "class")  
	@JsonSubTypes({  
	    @Type(value = Student.class, name = "com.example.Student"),  
	    @Type(value = Teacher.class, name = "com.example.Teacher"),
	    @Type(value = Admin.class, name = "com.example.Admin")})  
public abstract class Person {

    @Id
    @Column(nullable = false)
    private Integer pcn;
    //TODO private Long pcn / private string pcn

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;
    
    public Person() {
    }

    public Person(Integer pcn, String password, String email, String firstName, String lastName) {
        this.pcn = pcn;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getPcn() {
        return pcn;
    }

    public void setPcn(Integer pcn) {
        this.pcn = pcn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
