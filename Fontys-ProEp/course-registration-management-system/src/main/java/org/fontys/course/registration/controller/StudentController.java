package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.model.enums.StudentType;
import org.fontys.course.registration.service.StudentService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/addToGORCA", method = RequestMethod.POST, consumes = "application/json")
    public void AddStudents(@RequestBody List<Student> students) {
        this.studentService.AddStudents(students);
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
        students.add(new Student(3333, "123", "s.asnaashari@student.fontys.nl", "Samin", "Asnaashari", 333333, StudentType.REGULAR, Major.SOFTWARE, 9.0));
        students.add(new Student(2222, "111", "mervin@student.fontys.nl", "Mervin", "Vrolijk", 222222, StudentType.REGULAR, Major.SOFTWARE, 9.0));
        students.add(new Student(1111, "222", "agnes@student.fontys.nl", "Agnes", "Wadee", 111111, StudentType.REGULAR, Major.SOFTWARE, 9.0));
        students.add(new Student(6666, "333", "george@student.fontys.nl", "George", "Damianidis ", 666666, StudentType.REGULAR, Major.TECHNOLOGY, 8.0));
        students.add(new Student(7777, "random", "random@student.fontys.nl", "Joe", "Smith", 777777, StudentType.JELLYPICKING, Major.BUSINESS, 7.0));
        students.add(new Student(8888, "test", "lala@student.fontys.nl", "Alex", "Heijden", 888888, StudentType.RETAKE, Major.BUSINESS, 7.0));
        students.add(new Student(9999, "password", "example@student.fontys.nl", "Ann", "Williams", 999999, StudentType.JELLYPICKING, Major.TECHNOLOGY, 7.0));
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

    @RequestMapping(value = "/deleteStudents", method = RequestMethod.PUT) //TODO y not DELETE
    public void DeleteStudents(@RequestBody List<Student> students) {
        this.studentService.DeleteStudents(students);
    }

    @RequestMapping(value = "/addPushNotificationToken/{pushNotificationToken}", method = RequestMethod.PUT)
    public void AddPushNotificationToken(@PathVariable("pushNotificationToken") String pushNotificationToken, Principal principal) {
        this.studentService.AddPushNotificationToken(Integer.valueOf(principal.getName()), pushNotificationToken);
    }
    
    @RequestMapping(value = "/clearBadgeCount", method = RequestMethod.PUT)
    public void ClearAmounOfBadges(Principal principal) throws Exception {
        this.studentService.ClearAmountOfBadges(Integer.valueOf(principal.getName()));
    }
    
    @RequestMapping(value = "/getBadgeCount", method = RequestMethod.GET)
    public Integer GetAmounOfBadges(Principal principal) throws Exception {
        return this.studentService.GetAmountOfBadges(Integer.valueOf(principal.getName()));
    }
}
