package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.model.enums.StudentType;
import org.fontys.course.registration.service.StudentService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @RequestMapping(value = "/fontysStudents", method = RequestMethod.GET)
    public List<Student> GetAllFontysStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(310323, "234", "example@student.fontys.nl", "Samin", "Asnaashari", 271372, StudentType.REGULARSEMESTER, Major.SOFTWARE, 8.0));
        students.add(new Student(236478, "789", "blabla@student.fontys.nl", "Beer", "LaLa", 278383, StudentType.SECONDSEMESTER, Major.TECHNOLOGY, 6.0));
        students.add(new Student(37272, "738", "agnes@student.fontys.nl", "Agnes", "Wasee", 637367, StudentType.REGULARSEMESTER, Major.SOFTWARE, 8.0));
        students.add(new Student(18583, "74839", "tech@student.fontys.nl", "Dex", "Heijden", 382938, StudentType.SECONDSEMESTER, Major.BUSINESS, 7.0));
        students.add(new Student(244234, "random", "joe@student.fontys.nl", "Joe", "Smith", 212142, StudentType.RETAKE, Major.SOFTWARE, 6.0));
        students.add(new Student(921234, "test", "ben@student.fontys.nl", "Ben", "Doe", 122345, StudentType.REGULARSEMESTER, Major.BUSINESS, 3.0));
        students.add(new Student(343421, "password", "Ann@student.fontys.nl", "Ann", "Williams", 545672, StudentType.REGULARSEMESTER, Major.TECHNOLOGY, 5.0));
        return students;
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
