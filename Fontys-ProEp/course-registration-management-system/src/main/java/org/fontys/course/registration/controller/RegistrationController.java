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

    @RequestMapping(value = "/{courseCode}", method = RequestMethod.GET)
    public List<Registration> GetAllRegistrationsByCourse(@PathVariable String courseCode){
        return this.registrationService.GetAllRegistrationsByCourse(courseCode);
    }

    @RequestMapping(value = "/status/{registrationStatus}", method = RequestMethod.GET)
    public List<Registration> GetAllRegistrationsByStatus(@PathVariable RegistrationStatus registrationStatus){
        return this.registrationService.GetAllRegistrationByStatus(registrationStatus);
    }
}
