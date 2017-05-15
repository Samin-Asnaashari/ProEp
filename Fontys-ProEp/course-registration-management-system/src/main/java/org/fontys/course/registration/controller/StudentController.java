package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.service.StudentService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO decide what API documentation we want to use beside JAVADoc for documenting our codes.

@CrossOrigin
@RestController
@RequestMapping(value = "/students")
@Api(
        name = "Student model API",
        description = "provides the list of methods that manages the students.",
        stage = ApiStage.RC
)
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public void AddStudent(@RequestBody Student student) {
        this.studentService.AddStudent(student);
    }

    @RequestMapping(value = "/{pcn}", method = RequestMethod.GET)
    public Student GetStudent(@PathVariable Integer pcn) throws Exception {
        return this.studentService.GetStudent(pcn);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> GetAllStudents() {
        return this.studentService.GetAllStudents();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void UpdateStudent(@RequestBody Student student) {
        this.studentService.UpdateStudent(student);
    }

    @RequestMapping(value = "/{pcn}", method = RequestMethod.DELETE)
    public void DeleteStudent(@PathVariable Integer pcn) {
        this.studentService.DeleteStudent(pcn);
    }


}
