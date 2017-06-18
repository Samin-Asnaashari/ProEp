package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Teacher;
import org.fontys.course.registration.service.TeacherService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
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

    @RequestMapping(value = "/{pcn}", method = RequestMethod.GET)
    public Teacher GetTeacher(@PathVariable Integer pcn) throws Exception {
        return this.teacherService.GetTeacher(pcn);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Teacher> GetAllTeachers() {
        return this.teacherService.GetAllTeachers();
    }

    @RequestMapping(value = "/fontysTeachers", method = RequestMethod.GET)
    public List<Teacher> GetAllFontysTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(222, "123", "p.boots@fontys.nl", "Peter", "Boot", "https://apps.fhict.nl/hellotalent/Profile/I871412"));
        teachers.add(new Teacher(333, "password", "m.fransen@fontys.nl", "Marielle", "Fransen", "https://apps.fhict.nl/hellotalent/Profile/I872270"));
        teachers.add(new Teacher(666, "666", "j.geurts@fontys.nl", "Joris", "Geurts", "https://apps.fhict.nl/hellotalent/Profile/I878848"));
        teachers.add(new Teacher(777, "777", "li.li@fontys.nl", "Li", "Li", "https://apps.fhict.nl/hellotalent/Profile/I879556"));
        teachers.add(new Teacher(888, "888", "m.pesic@fontys.nl", "Maja", "Pesic", "https://apps.fhict.nl/hellotalent/profile/I884294"));
        teachers.add(new Teacher(999, "999", "o.figaroa@fontys.nl", "Figaroa", "Oswald", "https://apps.fhict.nl/hellotalent/Profile/I883146"));
        return teachers;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void UpdateTeacher(@RequestBody Teacher teacher) {
        this.teacherService.UpdateTeacher(teacher);
    }

    @RequestMapping(value = "/{pcn}", method = RequestMethod.DELETE)
    public void DeleteTeacher(@PathVariable Integer pcn) {
        this.teacherService.DeleteTeacher(pcn);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public List<Course> GetAllCoursesPerTeacher(Principal principal) throws Exception
    {
        return this.teacherService.GetTeacher(Integer.valueOf(principal.getName())).getMyCourses();
    }
    
    @RequestMapping(value = "/clearBadgeCount", method = RequestMethod.PUT)
    public void ClearAmounOfBadges(Principal principal) throws Exception {
        this.teacherService.ClearAmountOfBadges(Integer.valueOf(principal.getName()));
    }
    
    @RequestMapping(value = "/getBadgeCount", method = RequestMethod.GET)
    public Integer GetAmounOfBadges(Principal principal) throws Exception {
        return this.teacherService.GetAmountOfBadges(Integer.valueOf(principal.getName()));
    }

}
