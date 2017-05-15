package org.fontys.course.registration.service;

import java.util.ArrayList;
import java.util.Date;
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
            throw new Exception("Course with code=" + code + " does not exist.");
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
                throw new Exception("State with is=" + state.getId() + " does not exist.");
            } else {
                this.courseStateRepository.delete(state.getId());
            }
        }
    }

    @Transactional
    public Message RequestCourseDeletion(String id) {
        Course course = this.courseRepository.getOne(id);
        Date todayDate = new Date();
        //if today date's is after the course registration end date and it is before the registration starts
        // then delete is possible without sending notifications
        if (todayDate.after(course.getRegEndDate()) && todayDate.before(course.getRegStartDate())) {
            this.courseRepository.delete(course.getCode());
            return new Message("");
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
                        + " students that applied to this course");
            else {
                return new Message("");
            }
        }
    }

    @Transactional
    public void DeleteCourse(String id) {
        //Check to see if there is a list with persons to send notifications to before deleting
        List<Person> persons = this.utilService.GetPersonsToSendDeleteCourseNotifications().get(id);
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
        this.courseRepository.delete(id);
    }
}
