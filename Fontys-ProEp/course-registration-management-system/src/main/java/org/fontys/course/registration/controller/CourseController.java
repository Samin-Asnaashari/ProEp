package org.fontys.course.registration.controller;

import java.util.List;

import org.fontys.course.registration.exception.Message;
import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.CourseState;
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
        return this.courseService.GetAllCourses(); //TODO
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void UpdateCourse(@RequestBody Course course) {
        this.courseService.UpdateCourse(course);
    }

    @RequestMapping(value = "/addNewState/course/{code}", method = RequestMethod.POST)
    public void AddNewCourseStateToCourse(@RequestBody List<CourseState> states, @PathVariable String code) throws Exception {
        this.courseService.AddNewCourseStateToCourse(states, code);
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
    public void DeleteCourse(@PathVariable String id) {
        this.courseService.DeleteCourse(id);
    }
}
