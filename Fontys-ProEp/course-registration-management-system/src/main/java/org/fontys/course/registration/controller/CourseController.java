package org.fontys.course.registration.controller;

import org.fontys.course.registration.service.Interface.ICourseService;
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
    private ICourseService courseService;


    //TODO this is example. rest also have @PathVariable, @PathParam, @ApiPathParam(name = "id") is JsonDoc ,.......
//    @RequestMapping(value = secure, method = RequestMethod.POST, consumes = "application/json")
//    @ApiMethod(description = "Creates a new block with the given information.")
//    public Block createBlock(@RequestBody Block block) throws Exception {
//        return this.blockService.create(block);
//    }
}
