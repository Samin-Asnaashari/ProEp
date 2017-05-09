package org.fontys.course.registration.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.fontys.course.registration.model.Person;
import org.fontys.course.registration.service.PersonService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/persons")
@Api(
        name = "Person model API",
        description = "provides the list of methods that manages persons.",
        stage = ApiStage.RC
    )
public class PersonController {

    @Autowired
    private PersonService personService;
    @RequestMapping("/students")
    public List<Person> GetAllStudents(){
        return this.personService.GetAllStudents();
    }

    @RequestMapping("/teachers")
    public List<Person> GetAllTeachers(){
        return this.personService.GetAllTeachers();
    }

    @RequestMapping("/teachers/{pcn}")
    public Person GetTeacher(@PathVariable Integer pcn) {
        return this.personService.GetTeacher(pcn);
    }

    @RequestMapping("/students/{pcn}")
    public Person GetStudent(@PathVariable Integer pcn) {return this.personService.GetStudent(pcn); }

    @RequestMapping(method= RequestMethod.POST)
    public void AddPerson(@RequestBody Person person){
        this.personService.AddPerson(person);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public void UpdateCourse(@RequestBody Person person){
        this.personService.UpdatePerson(person);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public void DeleteCourse(@PathVariable Integer pcn){
        this.personService.DeletePerson(pcn);
    }
}
