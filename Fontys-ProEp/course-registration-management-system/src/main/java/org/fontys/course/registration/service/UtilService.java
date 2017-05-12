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
	
	public HashMap<String, List<Person>> getPersonsToSendDeleteCourseNotifications() {
		return personsToSendDeleteCourseNotifications;
	}

	public void setPersonsToSendDeleteCourseNotifications(
			HashMap<String, List<Person>> personsToSendDeleteCourseNotifications) {
		this.personsToSendDeleteCourseNotifications = personsToSendDeleteCourseNotifications;
	}

	public void AddNewHashMapEntryForPersonsToSendDeleteCourseNotifications(String courseCode, List<Person> persons){
		if(this.personsToSendDeleteCourseNotifications == null)
			this.personsToSendDeleteCourseNotifications = new HashMap<>();
		this.personsToSendDeleteCourseNotifications.put(courseCode, persons);
	}
	
	public List<Student> GetAllStudentsByCourse(String courseCode){
		List<Registration> registrations = this.registrationService.GetAllRegistrations();
		List<Student> studentsApplied = new ArrayList<>();
		for(int i = 0; i < registrations.size(); i++){
			if(registrations.get(i).getId().getCourse().getCode().equals(courseCode)){
				studentsApplied.add(registrations.get(i).getId().getStudent());
			}
		}
		return studentsApplied;
	}
	
	public void AddNewNotification(Notification notification){
		this.notificationService.AddNotification(notification);
	}
	
	public void AddNewNotifications(List<Notification> notifications){
		this.notificationService.AddNotifications(notifications);
	}
}
