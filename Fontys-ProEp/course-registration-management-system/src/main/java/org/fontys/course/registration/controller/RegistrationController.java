package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.fontys.course.registration.service.RegistrationService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/registrations")
@Api(
        name = "Teacher model API",
        description = "provides the list of methods that manages the teachers.",
        stage = ApiStage.RC
)
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.POST)
    public void createRegistration(@PathVariable Registration registration) {
        this.registrationService.createRegistration(registration);
    }

    @RequestMapping(value = "/{courseCode}", method = RequestMethod.GET)
    public List<Registration> GetAllRegistrationsByCourse(@PathVariable String courseCode) {
        return this.registrationService.GetAllRegistrationsByCourse(courseCode);
    }

    @RequestMapping(value = "/status/{registrationStatus}/{courseCode}", method = RequestMethod.GET)
    public List<Registration> GetAllRegistrationsByStatus(@PathVariable("registrationStatus") RegistrationStatus registrationStatus,
    		@PathVariable("courseCode") String courseCode){
        try {
			return this.registrationService.GetAllRegistrationByStatusAndCourse(registrationStatus, courseCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}
