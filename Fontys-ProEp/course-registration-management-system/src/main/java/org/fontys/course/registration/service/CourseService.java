package org.fontys.course.registration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.fontys.course.registration.exception.Message;
import org.fontys.course.registration.model.*;
import org.fontys.course.registration.repository.CourseRepository;
import org.fontys.course.registration.repository.CourseStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseStateRepository courseStateRepository;

    @Autowired
    private UtilService utilService;

    @Transactional
    public void AddCourse(Course course) {
        this.courseRepository.save(course);
    }

    public Course GetCourse(String code) throws Exception {
        Course course = this.courseRepository.findOne(code);
        if (course == null) {
            throw new Exception("Course with code = " + code + " does not exist.");
        } else {
            return course;
        }
    }

    public List<Course> GetAllCourses() {
        return this.courseRepository.findAll();
    }

    @Transactional
    public void UpdateCourse(Course course) {
        this.courseRepository.save(course);
    }

    @Transactional
    public void AddNewCourseStateToCourse(List<CourseState> courseStates, String courseCode) throws Exception {
        Course course = this.GetCourse(courseCode);
        for (CourseState state : courseStates) {
            state.setCourse(course);
            this.courseStateRepository.save(state);
        }
    }

    @Transactional
    public void RemoveCourseStateFromCourse(List<CourseState> courseStates) throws Exception {
        for (CourseState stateToDelete : courseStates) {
            CourseState state = this.courseStateRepository.findOne(stateToDelete.getId());
            if (state == null) {
                throw new Exception("State with id = " + stateToDelete.getId() + " does not exist.");
            } else {
                this.courseStateRepository.delete(stateToDelete.getId());
            }
        }
    }

    @Transactional
    public Message RequestCourseDeletion(String id) {
        Course course = this.courseRepository.getOne(id);
        Date todayDate = new Date();
        String defaultMsg = "Are you sure you want do delete course: " + course.getCode() + "?";
        //if today date's is after the course registration end date and it is before the registration starts
        // then delete is possible without sending notifications
        Date regEndDate = course.getRegEndDate();
        Date regStartDate = course.getRegStartDate();
        if(regEndDate != null || regStartDate != null) {
            if (todayDate.after(regEndDate) && todayDate.before(regStartDate)) {
                return new Message(defaultMsg);
            } else {
                //send notifications that course has been dropped to all teachers and students
                //this is also checked and this info is sent to the admin before confirming to delete
                //so he knows that there are already students that requested registering to this course

                List<Person> personsToSendNotifications = new ArrayList<>();

                List<Student> studentsToSendNotifications = this.utilService.GetAllStudentsByCourse
                        (course.getCode());

                for (int i = 0; i < studentsToSendNotifications.size(); i++) {
                    personsToSendNotifications.add(studentsToSendNotifications.get(i));
                }

                for (int i = 0; i < course.getTeachers().size(); i++) {
                    personsToSendNotifications.add(course.getTeachers().get(i));
                }

                this.utilService.AddNewHashMapEntryForPersonsToSendDeleteCourseNotifications(course.getCode(),
                        personsToSendNotifications);

                if (studentsToSendNotifications.size() != 0)
                    return new Message("Warning: There are already " + studentsToSendNotifications.size()
                            + " students that applied to this course. " + defaultMsg);
                else {
                    return new Message(defaultMsg);
                }
            }
        }
        else {
            return new Message(defaultMsg);
        }
    }

    @Transactional
    public void DeleteCourse(String id) {
        //Check to see if there is a list with persons to send notifications to before deleting
        HashMap<String, List<Person>> personsToSendDeleteCourseNotifications = this.utilService.GetPersonsToSendDeleteCourseNotifications();
        if (personsToSendDeleteCourseNotifications != null) {
            List<Person> persons = personsToSendDeleteCourseNotifications.get(id);
            if (persons != null) {
                if (persons.size() != 0) {
                    String notificationContent = " is not available anymore in this block, sorry for any inconvenience";
                    if (persons.size() == 1) {
                        Notification notification = new Notification
                                (id + notificationContent, new Date(), null
                                     /*need to find a way to get the admin user logged in*/, persons.get(0));
                        this.utilService.AddNewNotification(notification);
                    } else {
                        List<Notification> notifications = new ArrayList<>();
                        for (int i = 0; i < persons.size(); i++) {
                            notifications.add(new Notification(id + notificationContent, new Date(), null
                                     /*need to find a way to get the admin user logged in*/, persons.get(i)));
                        }
                        this.utilService.AddNewNotifications(notifications);
                    }
                }
            }
        }
        this.courseRepository.delete(id);
    }
    
    @Transactional
    public void DeleteTeacherFromCourse(Integer pcn, String courseCode) throws Exception {
    	Teacher teacher = this.utilService.GetTeacher(pcn);
    	Course course = this.courseRepository.findOne(courseCode);
    	course.getTeachers().remove(teacher);
    }
    
    @Transactional
    public void AddTeacherToCourse(Teacher teacher, String courseCode){
    	Course course = this.courseRepository.findOne(courseCode);
    	course.getTeachers().add(teacher);
    }
    
    @Transactional
    public void AddTeachersToCourse(List<Teacher> teachers, String courseCode){
    	Course course = this.courseRepository.findOne(courseCode);
    	for (int i = 0; i < teachers.size(); i++) {
    		course.getTeachers().add(teachers.get(i));
    	}
    }
}
