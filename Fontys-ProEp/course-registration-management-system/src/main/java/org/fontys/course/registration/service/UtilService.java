package org.fontys.course.registration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.model.Person;
import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private NotificationService notificationService;

    private HashMap<String, List<Person>> personsToSendDeleteCourseNotifications;

    public void AddNewNotification(Notification notification) {
        this.notificationService.AddNotification(notification);
    }

    public void AddNewNotifications(List<Notification> notifications) {
        this.notificationService.AddNotifications(notifications);
    }

    public void AddNewHashMapEntryForPersonsToSendDeleteCourseNotifications(String courseCode, List<Person> persons) {
        if (this.personsToSendDeleteCourseNotifications == null)
            this.personsToSendDeleteCourseNotifications = new HashMap<>();
        this.personsToSendDeleteCourseNotifications.put(courseCode, persons);
    }

    public HashMap<String, List<Person>> GetPersonsToSendDeleteCourseNotifications() {
        return personsToSendDeleteCourseNotifications;
    }

    public List<Student> GetAllStudentsByCourse(String courseCode) {
        List<Registration> registrations = this.registrationService.GetAllRegistrationsByCourse(courseCode);
        List<Student> studentsApplied = new ArrayList<>();
        for (int i = 0; i < registrations.size(); i++) {
            studentsApplied.add(registrations.get(i).getId().getStudent());
        }
        return studentsApplied;
    }

    public List<Registration> GetAllRegistrationsByCourse(String courseCode) {
        return this.registrationService.GetAllRegistrationsByCourse(courseCode);
    }

    public void SetPersonsToSendDeleteCourseNotifications(
            HashMap<String, List<Person>> personsToSendDeleteCourseNotifications) {
        this.personsToSendDeleteCourseNotifications = personsToSendDeleteCourseNotifications;
    }

    private Person GetPersonById(Integer pcn){
        Person person = this.teacherService.GetTeacherUnsafe(pcn);

        if(person == null)
            person = this.studentService.GetStudentUnsafe(pcn);

        return person;
    }

    public boolean CheckUsernameAndPassword(Integer pcn, String pass) throws Exception
    {
        Person person = GetPersonById(pcn);

        if(person == null)
            throw new Exception("PCN doesn't exist");

        return person.getPassword().equals(pass);
    }
}
