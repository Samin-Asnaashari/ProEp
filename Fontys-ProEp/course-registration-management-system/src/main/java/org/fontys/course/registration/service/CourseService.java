package org.fontys.course.registration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fontys.course.registration.exception.Message;
import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Person;
import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.model.Notification;
import org.fontys.course.registration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private UtilService utilService;

    @Transactional
    public void AddCourse(Course course) {
        this.courseRepository.save(course);
    }

    public Course GetCourse(String id) {
        return this.courseRepository.findOne(id);
    }

    public List<Course> GetAllCourses() {
        return this.courseRepository.findAll();
    }

    @Transactional
    public void UpdateCourse(Course course) {
        this.courseRepository.save(course);
    }

    public Message RequestCourseDeletion(String id){
    	Course course = this.courseRepository.getOne(id);
    	Date todayDate = new Date();
    	//if today date's is after the course registration end date and it is before the registration starts
    	// then delete is possible without sending notifications
    	if(todayDate.after(course.getRegEndDate()) && todayDate.before(course.getRegStartDate())) {
    		this.courseRepository.delete(course.getCode());
    		return new Message("");
    	}
    	else{
    		//send notifications that course has been dropped to all teachers and students
    		//this is also checked and this info is sent to the admin before confirming to delete
    		//so he knows that there are already students that requested registering to this course
    		List<Student> studentsToSendNotifications = this.utilService.GetAllStudentsByCourse
    				(course.getCode());
    		List<Person> personsToSendNotifications = new ArrayList<>();
    		for(int i = 0; i < studentsToSendNotifications.size(); i++){
    			personsToSendNotifications.add(studentsToSendNotifications.get(i));
    		}
    		for(int i = 0; i < course.getTeachers().size(); i++){
    			personsToSendNotifications.add(course.getTeachers().get(i));
    		}
    		this.utilService.AddNewHashMapEntryForPersonsToSendDeleteCourseNotifications(course.getCode(), 
    				personsToSendNotifications);
    		System.out.println("Size:" + studentsToSendNotifications.size());
    		if(studentsToSendNotifications.size() != 0)
    			return new Message("Warning: There are already " + studentsToSendNotifications.size() 
    			+ " students that applied to this course");
    		else
    			return new Message("");
    	}
    }
    
    @Transactional
    public void DeleteCourse(String id) {
    	//Check to see if there is a list with persons to send notifications to before deleting
    	List<Person> persons = this.utilService.getPersonsToSendDeleteCourseNotifications().get(id);
    	System.out.println("DeleteMethod: " + persons.size());
    	if(persons != null){
    		if(persons.size() != 0){
    			String notificationContent = " is not available anymore in this block, sorry for any inconvenience";
    			if(persons.size() == 1){
    				 Notification notification = new Notification
    						 (id + notificationContent, new Date(), null
    								 /*need to find a way to get the admin user logged in*/, persons.get(0));
    				 this.utilService.AddNewNotification(notification);
    			}
    			else{
    				List<Notification> notifications = new ArrayList<>();
    				for(int i = 0; i < persons.size(); i++){
    					notifications.add(new Notification(id + notificationContent, new Date(), null
    								 /*need to find a way to get the admin user logged in*/, persons.get(i)));
    				}
    				this.utilService.AddNewNotifications(notifications);
    			}
    		}
    	}
    	this.courseRepository.delete(id);
    }
}
