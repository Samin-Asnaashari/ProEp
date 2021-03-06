package org.fontys.course.registration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fontys.course.registration.model.*;
import org.fontys.course.registration.model.enums.NotificationType;
import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.fontys.course.registration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UtilService utilService;

    @Transactional
    public boolean createRegistration(String courseCode, int pcn) throws Exception {
        Course course = utilService.GetCourse(courseCode);
        Date now = new Date();
        if (course.getRegStartDate() != null && course.getRegEndDate() != null) {
            if (now.after(course.getRegStartDate()) && now.before(course.getRegEndDate())) {
                Student student = utilService.GetStudentById(pcn);
                Registration newRegistration = new Registration(new RegistrationId(student, course), new Date(), RegistrationStatus.PENDING);
                this.registrationRepository.save(newRegistration);
                String notificationContent = "Student with PCN " + pcn + " requested registration for course " + courseCode;
                for (Teacher courseTeacher : course.getTeachers()) {
                    this.utilService.AddNewNotification(new Notification(NotificationType.REGISTERED, notificationContent, new Date(), student, courseTeacher, courseCode));
                }
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void dropRegistration(String courseCode, Integer pcn) throws Exception {
        Course course = utilService.GetCourse(courseCode);
        Student student = utilService.GetStudentById(pcn);
        Registration reg =
                registrationRepository.findById(new RegistrationId
                        (student, course));
        String notificationContent = "Student with PCN " + pcn + " Dropped Course " + courseCode;
        for (Teacher courseTeacher : utilService.GetCourse(courseCode).getTeachers()) {
            this.utilService.AddNewNotification(new Notification(NotificationType.DROPPED, notificationContent, new Date(), student, courseTeacher, courseCode));
        }
        this.registrationRepository.delete(reg);
    }

    @Transactional
    public void CancelRegistration(String courseCode, Integer pcn) throws Exception {
        Course course = utilService.GetCourse(courseCode);
        Student student = utilService.GetStudentById(pcn);
        Registration reg =
                registrationRepository.findById(new RegistrationId
                        (student, course));
        String notificationContent = "Student with PCN " + pcn + " cancelled his/her request for Course " + courseCode;
        for (Teacher courseTeacher : utilService.GetCourse(courseCode).getTeachers()) {
            this.utilService.AddNewNotification(new Notification(NotificationType.CANCELED, notificationContent, new Date(), student, courseTeacher, courseCode));
        }
        this.registrationRepository.delete(reg);
    }

    public List<Registration> GetAllRegistrations() {
        return this.registrationRepository.findAll();
    }

    public List<Registration> GetAllRegistrationsByCourse(String courseCode) {
        try {
            return this.registrationRepository.findByIdCourse(this.utilService.GetCourse(courseCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Registration> GetAllRegistrationByStatusAndCourse(RegistrationStatus registrationStatus, String courseCode) throws Exception {
        return this.registrationRepository.findByRegistrationStatusAndIdCourse(registrationStatus, this.utilService.GetCourse(courseCode));
    }

    @Transactional
    public void UpdateRegistration(String courseCode, List<Integer> studentPcnList, String status, Integer teacherPCN) throws Exception {
        Course course = this.utilService.GetCourse(courseCode);
        Person sender = this.utilService.GetPersonById(teacherPCN);
        Registration registration = null;
        RegistrationStatus oldStatus = null;
        		
        for (int i = 0; i < studentPcnList.size(); i++) {
        	Student student = this.utilService.GetStudentById(studentPcnList.get(i));
        	this.utilService.AddNewNotification(new Notification(NotificationType.valueOf(status), "You have been " + status.toLowerCase() + " in " + courseCode + " by " + sender.getFirstName(), new Date(), 
            		sender, student, courseCode));
            registration = this.registrationRepository.findById(new RegistrationId(student, course));
            oldStatus = registration.getRegistrationStatus();
            registration.setRegistrationStatus(RegistrationStatus.valueOf(status));
            
            if(NotificationType.valueOf(status).equals(NotificationType.ACCEPTED)) {
            	course.setFilledSeat(course.getFilledSeat() + 1);
            }
            else {
            	if(oldStatus != null) {
            		if(oldStatus.equals(RegistrationStatus.ACCEPTED)) {
            			course.setFilledSeat(course.getFilledSeat() - 1);
            		}
            	}
            }
        }
    }

    public List<Registration> GetAllRegistrationsByPcn(Integer studentPcn) {
        return this.registrationRepository.findByIdStudent(this.utilService.GetStudentById(studentPcn));
    }

    public List<Course> GetAllAppliedElectiveCoursesByPcn(Integer studentPcn) {
        List<Registration> registrations = this.registrationRepository.findByIdStudent(this.utilService.GetStudentById(studentPcn));
        List<Course> courses = new ArrayList<>();

        for (Registration reg : registrations)
            courses.add(reg.getId().getCourse());

        return courses;
    }

    public List<Registration> GetAllRegistrationsByPcnWithStatus(Integer studentPcn, RegistrationStatus status) {
        return this.registrationRepository.findByIdStudentAndRegistrationStatus(this.utilService.GetStudentById(studentPcn), status);
    }

    public List<Registration> GetAllElectiveCoursesByPcnWithFilteredStatus(Integer studentPcn, RegistrationStatus registrationStatus) {
        return this.registrationRepository.findByIdStudentAndRegistrationStatusNot(this.utilService.GetStudentById(studentPcn), registrationStatus);
    }

    @Transactional
    public void DeleteAllRegistrationsByStudent(Integer pcn) {
        this.registrationRepository.deleteByIdStudent(this.utilService.GetStudentById(pcn));
    }

    @Transactional
    public void DeleteAllRegistrationsByCourse(String courseCode) throws Exception {
        this.registrationRepository.deleteByIdCourse(this.utilService.GetCourse(courseCode));
    }
}
