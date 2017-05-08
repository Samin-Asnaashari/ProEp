package org.fontys.course.registration.controller;

import java.util.List;

import org.fontys.course.registration.model.Course;
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

    @RequestMapping("")
    public List<Course> GetAllCourses(){
    	return this.courseService.GetAllCourses();
    }
    
    @RequestMapping("/{id}")
    public Course GetCourse(@PathVariable String id) {
		return this.courseService.GetCourse(id);
	}
    
    @RequestMapping(method=RequestMethod.POST)
    public void AddCourse(@RequestBody Course course){
    	this.courseService.AddCourse(course);
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public void UpdateCourse(@RequestBody Course course){
    	this.courseService.UpdateCourse(course);
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public void DeleteCourse(@PathVariable String id){
    	this.courseService.DeleteCourse(id);
    }
    
    //TODO this is example. rest also have @PathVariable, @PathParam, @ApiPathParam(name = "id") is JsonDoc ,.......
//    @RequestMapping(value = secure, method = RequestMethod.POST, consumes = "application/json")
//    @ApiMethod(description = "Creates a new block with the given information.")
//    public Block createBlock(@RequestBody Block block) throws Exception {
//        return this.blockService.create(block);
//    }
}
