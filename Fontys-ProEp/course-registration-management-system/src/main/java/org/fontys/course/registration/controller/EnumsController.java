package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.enums.CourseType;
import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.service.EnumsService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/enums")
@Api(
        name = "Enums model API",
        description = "provides the list of methods that manages the enums.",
        stage = ApiStage.RC
)
public class EnumsController {

    @Autowired
    private EnumsService enumsService;

    @RequestMapping(value = "/courseTypes", method = RequestMethod.GET)
    public CourseType[] GetCourseTypePossibleValues() {
        return this.enumsService.GetCourseTypePossibleValues();
    }

    @RequestMapping(value = "/majors", method = RequestMethod.GET)
    public Major[] GetMajorPossibleValues() {
        return this.enumsService.GetMajorPossibleValues();
    }
}
