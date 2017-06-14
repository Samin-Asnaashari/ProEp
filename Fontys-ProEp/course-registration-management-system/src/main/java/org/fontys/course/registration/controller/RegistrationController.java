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

    @RequestMapping(value = "/updateRegistrationStatus/{courseCode}/{studentPcn}/{registrationStatus}",
            method = RequestMethod.PUT)
    public void UpdateRegistration(@PathVariable("courseCode") String courseCode,@PathVariable("studentPcn") String studentPcn,
                                   @PathVariable("registrationStatus") String registrationStatus ) {
        try {
            this.registrationService.UpdateRegistration(courseCode,studentPcn,registrationStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/pcn/{pcn}", method = RequestMethod.GET)
    public List<Registration> GetAllRegistrationsByStudent(@PathVariable Integer pcn){
        return this.registrationService.GetAllRegistrationsByPcn(pcn);
    }

    @RequestMapping(value = "/ascourses/pcn/{pcn}", method = RequestMethod.GET)
    public List<Course> GetAllRegistrationsByStudentAsCourses(@PathVariable Integer pcn){
        return this.registrationService.GetAllAppliedElectiveCoursesByPcn(pcn);
    }

    @RequestMapping(value = "/accepted/pcn/{pcn}", method = RequestMethod.GET)
    public List<Registration> GetAllAcceptedRegistrationsByStudent(@PathVariable Integer pcn){
        return this.registrationService.GetAllAcceptedRegistrationsByPcn(pcn);
    }

    @RequestMapping(value = "/exceptAcceptedOnes", method = RequestMethod.GET)
    public List<Registration> GetAllRegistrationsExceptAcceptedOnes(Principal principal){
        return this.registrationService.GetAllElectiveCoursesByPcnWithFilteredStatus(Integer.valueOf(principal.getName()),RegistrationStatus.ACCEPTED);
    }

    //@RequestMapping(value = "/accepted/ascourses/pcn/{pcn}", method = RequestMethod.GET)
//    @RequestMapping(value = "/accepted/ascourses/pcn/", method = RequestMethod.GET)
//    public List<Course> GetAllAcceptedRegistrationsByStudentAsCourses(Principal principal){
//        return this.registrationService.GetAllAcceptedElectiveCoursesByPcn(Integer.valueOf(principal.getName()));
//    }
}
