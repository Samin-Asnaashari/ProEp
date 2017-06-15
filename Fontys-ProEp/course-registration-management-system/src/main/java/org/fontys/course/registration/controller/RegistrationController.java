package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.fontys.course.registration.service.RegistrationService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @RequestMapping(value = "/{courseCode}", method = RequestMethod.POST)
    public void createRegistration(@PathVariable String courseCode, Principal principal) throws Exception {
        this.registrationService.createRegistration(courseCode, Integer.valueOf(principal.getName()));
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
			e.printStackTrace();
		}
        return null;
    }

	@RequestMapping(value = "/updateRegistrationStatus/{courseCode}/{studentPcnList}/{registrationStatus}", method = RequestMethod.PUT)
	public void UpdateRegistration(@PathVariable("courseCode") String courseCode,
			@PathVariable("studentPcnList") List<Integer> studentPcnList,
			@PathVariable("registrationStatus") String registrationStatus) {
		try {
			this.registrationService.UpdateRegistration(courseCode, studentPcnList, registrationStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @RequestMapping(value = "/exceptAcceptedOnes", method = RequestMethod.GET)
    public List<Registration> GetAllRegistrationsExceptAcceptedOnes(Principal principal){
        return this.registrationService.GetAllElectiveCoursesByPcnWithFilteredStatus(Integer.valueOf(principal.getName()),RegistrationStatus.ACCEPTED);
    }

    @RequestMapping(value = "/drop/{courseCode}", method = RequestMethod.GET)
    public void DropRegistration(@PathVariable String courseCode, Principal principal){
        try {
            this.registrationService.dropRegistration(courseCode, Integer.valueOf(principal.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
