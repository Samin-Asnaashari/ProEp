package org.fontys.course.registration.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.fontys.course.registration.exception.Message;
import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.CourseState;
import org.fontys.course.registration.model.Teacher;
import org.fontys.course.registration.model.enums.CourseType;
import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.fontys.course.registration.service.CourseService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//TODO decide what API documentation we want to use beside JAVADoc for documenting our codes.

@CrossOrigin
@RestController
@RequestMapping(value = "/courses")
@Api(
        name = "Course model API",
        description = "provides the list of methods that manages the courses.",
        stage = ApiStage.RC
)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public void AddCourse(@RequestBody Course course) {
        this.courseService.AddCourse(course);
    }

    @RequestMapping(value = "/mandatory", method = RequestMethod.GET)
    public List<Course> GetMandatoryCourses(Principal principal) throws Exception {
        return this.courseService.GetCoursesByCourseType(Integer.valueOf(principal.getName()), CourseType.MANDATORY);
    }

    @RequestMapping(value = "/elective", method = RequestMethod.GET)
    public List<Course> GetElectiveCourses(Principal principal) throws Exception {
        return this.courseService.GetCoursesByCourseType(Integer.valueOf(principal.getName()), CourseType.ELECTIVE);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public Course GetCourse(@PathVariable String code) throws Exception {
        return this.courseService.GetCourse(code);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Course> GetAllCourses() {
        return this.courseService.GetAllCourses();
    }

    @RequestMapping(value = "/fontysCourses", method = RequestMethod.GET)
    public List<Course> GetListOfCourses() {
        List<Course> fontysCourses = new ArrayList<>(); //
        fontysCourses.add(new Course("ES1", "Embedded System", "description", 3, 7, 17, 6, null, null));
        fontysCourses.add(new Course("ProEp", "Project", "Last Project of your Last Semester as ICT Student", 7, 7, 30, 32, null, null));
        fontysCourses.add(new Course("IPV", "Image Processing", "Not Added yet edited...", 3, 7, 26, 3, null, null));
        fontysCourses.add(new Course("MOB", "Mobile Application", "des", 3, 4, 10, 7, null, null));
        fontysCourses.add(new Course("T&H", "Trends and Hypes", "blabla", 2, 14, 23, 4, null, null));
        fontysCourses.add(new Course("MATH", "Mathematic", "Fun", 3, 5, 17, 6, null, null));
        return fontysCourses;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void UpdateCourse(@RequestBody Course course) {
        this.courseService.UpdateCourse(course);

    }

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    public void AddNewCourseStateToCourse(@PathVariable String token) throws Exception {
        System.out.println(token);
    }

    @RequestMapping(value = "/removeState", method = RequestMethod.PUT)
    public void RemoveCourseStateFromCourse(@RequestBody List<CourseState> states) throws Exception {
        this.courseService.RemoveCourseStateFromCourse(states);
    }

    @RequestMapping(value = "/requestDeletion/{id}", method = RequestMethod.GET)
    public Message RequestCourseDeletion(@PathVariable String id) {
        return this.courseService.RequestCourseDeletion(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void DeleteCourse(@PathVariable String id, Principal principal) {
        this.courseService.DeleteCourse(id, Integer.valueOf(principal.getName()));
    }

    @RequestMapping(value = "/accepted", method = RequestMethod.GET)
    public List<Course> GetAllAcceptedRegistrationsByStudentAsCourses(Principal principal) {
        return this.courseService.GetAcceptedElectiveCourses(Integer.valueOf(principal.getName()));
    }

    @RequestMapping(value = "/elective/forApply", method = RequestMethod.GET)
    public List<Course> GetAllNotAppliedElectiveCoursesForStudent(Principal principal) {
        return this.courseService.findAllNotAppliedElectiveCoursesForStudent(Integer.valueOf(principal.getName()));
    }
}
