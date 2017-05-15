package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.Teacher;
import org.fontys.course.registration.service.TeacherService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/teachers")
@Api(
        name = "Teacher model API",
        description = "provides the list of methods that manages the teachers.",
        stage = ApiStage.RC
)
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public void AddTeacher(@RequestBody Teacher teacher) {
        this.teacherService.AddTeacher(teacher);
    }

    @RequestMapping(value = "/{pcn}")
    public Teacher GetTeacher(@PathVariable Integer pcn) throws Exception {
        return this.teacherService.GetTeacher(pcn);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Teacher> GetAllTeachers() {
        return this.teacherService.GetAllTeachers();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void UpdateTeacher(@RequestBody Teacher teacher) {
        this.teacherService.UpdateTeacher(teacher);
    }

    @RequestMapping(value = "/{pcn}", method = RequestMethod.DELETE)
    public void DeleteTeacher(@PathVariable Integer pcn) {
        this.teacherService.DeleteTeacher(pcn);
    }


}
