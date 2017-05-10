package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.model.Teacher;
import org.fontys.course.registration.service.TeacherService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO decide what API documentation we want to use beside JAVADoc for documenting our codes.

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

    @RequestMapping(method=RequestMethod.POST)
    public void AddStudent(@RequestBody Teacher teacher){this.teacherService.AddTeacher(teacher); }

    @RequestMapping("/{pcn}")
    public Teacher GetTeacher(@PathVariable Integer pcn) {
        return this.teacherService.GetTeacher(pcn);
    }

    @RequestMapping("")
    public List<Teacher> GetAllTeachers(){return this.teacherService.GetAllTeachers();  }

    @RequestMapping(method=RequestMethod.PUT)
    public void UpdateStudent(@RequestBody Teacher teacher){ this.teacherService.UpdateTeacher(teacher);  }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/{pcn}")
    public void DeleteStudent(@PathVariable Integer pcn){
    	this.teacherService.DeleteTeacher(pcn);
    }
    

}
