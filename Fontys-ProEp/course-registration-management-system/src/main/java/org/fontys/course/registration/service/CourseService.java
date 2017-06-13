package org.fontys.course.registration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.fontys.course.registration.exception.Message;
import org.fontys.course.registration.model.*;
import org.fontys.course.registration.model.enums.RegistrationStatus;
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
        for (CourseState state : course.getStates()) {
            if(state.getId() == null){
                state.setCourse(course);
                this.courseStateRepository.save(state);
            }
        }
        
        List<Teacher> teachers = course.getTeachers();
        for (int i = 0; i < teachers.size(); i++) {
        	Teacher teacher = teachers.get(i);
        	teacher.setNotificationBadgeCount(0);
			this.utilService.AddNewTeacher(teacher);
		}
        
        this.courseRepository.save(course);
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
		String defaultMsg = "Are you sure you want do delete course: " + course.getCode() + "?";

		List<Person> personsToSendNotifications = new ArrayList<>();

		List<Student> acceptedStudents = this.utilService
				.GetAllStudentsByRegistrationStatusAndCourse(RegistrationStatus.ACCEPTED, course.getCode());
		
		List<Student> pendingStudents = this.utilService
				.GetAllStudentsByRegistrationStatusAndCourse(RegistrationStatus.PENDING, course.getCode());

		for (int i = 0; i < acceptedStudents.size(); i++) {
			personsToSendNotifications.add(acceptedStudents.get(i));
		}

		for (int i = 0; i < pendingStudents.size(); i++) {
			personsToSendNotifications.add(pendingStudents.get(i));
		}

		for (int i = 0; i < course.getTeachers().size(); i++) {
			personsToSendNotifications.add(course.getTeachers().get(i));
		}

		this.utilService.AddNewHashMapEntryForPersonsToSendDeleteCourseNotifications(course.getCode(),
				personsToSendNotifications);

		if (acceptedStudents.size() != 0)
			return new Message("Warning: There are already " + acceptedStudents.size()
					+ " students that applied to this course. " + defaultMsg);
		else {
			return new Message(defaultMsg);
		}
	}

    @Transactional
    public void DeleteCourse(String courseCode, Integer sender) {
        //Check to see if there is a list with persons to send notifications to before deleting
        HashMap<String, List<Person>> personsToSendDeleteCourseNotifications = this.utilService.GetPersonsToSendDeleteCourseNotifications();
        if (personsToSendDeleteCourseNotifications != null) {
            List<Person> persons = personsToSendDeleteCourseNotifications.get(courseCode);
            if (persons != null) {
                if (persons.size() != 0) {
                    String notificationContent = " is not available anymore in this block, sorry for any inconveniences.";
                    if (persons.size() == 1) {
                        Notification notification = new Notification
                                (courseCode + notificationContent, new Date(), this.utilService.GetAdminById(sender.toString()), persons.get(0));
                        this.utilService.AddNewNotification(notification);
                    } else {
                        List<Notification> notifications = new ArrayList<>();
                        for (int i = 0; i < persons.size(); i++) {
                            notifications.add(new Notification(courseCode + notificationContent, new Date(), this.utilService.GetAdminById(sender.toString()), persons.get(i)));
                        }
                        this.utilService.AddNewNotifications(notifications);
                    }
                }
            }
        }
        this.courseRepository.delete(courseCode);
    }


}
