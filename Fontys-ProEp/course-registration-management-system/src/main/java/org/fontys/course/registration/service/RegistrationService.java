package org.fontys.course.registration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.RegistrationId;
import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.fontys.course.registration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UtilService utilService;

    @Transactional
    public void createRegistration(String courseCode, int pcn) throws Exception {
        Student s = utilService.GetStudentById(pcn);
        Course c = utilService.GetCourse(courseCode);
        RegistrationId id = new RegistrationId();
        Registration newRegistration = new Registration(s.getPcn(),c.getCode(),new Date(),RegistrationStatus.PENDING);
        this.registrationRepository.save(newRegistration);
    }

    public List<Registration> GetAllRegistrations() {
        return this.registrationRepository.findAll();
    }

    public List<Registration> GetAllRegistrationsByCourse(String courseCode) {
        return this.registrationRepository.findRegistrationByCourse(courseCode);
    }

    public List<Registration> GetAllRegistrationByStatusAndCourse(RegistrationStatus registrationStatus, String courseCode) throws Exception {
        return this.registrationRepository.findByRegistrationStatusAndCourse(registrationStatus, utilService.GetCourse(courseCode));
    }

    @Transactional
    public void UpdateRegistration(String courseCode, String studentPcn, String status) throws Exception {
        Registration registration=this.registrationRepository.findByCourseAndStudent(utilService.GetCourse(courseCode),utilService.GetStudentById(studentPcn));
        registration.setRegistrationStatus(RegistrationStatus.valueOf(status));
    }

    public List<Registration> GetAllRegistrationsByPcn(Integer pcn) {
        return this.registrationRepository.findByStudent_Pcn(pcn);
    }

    public List<Course> GetAllAppliedElectiveCoursesByPcn(Integer pcn) {
        List<Registration> registrations = this.registrationRepository.findByStudent_Pcn(pcn);
        List<Course> courses = new ArrayList<>();

        for (Registration reg : registrations)
            courses.add(reg.getCourse());

        return courses;
    }

    public List<Registration> GetAllRegistrationsByPcnWithStatus(Integer pcn, RegistrationStatus status) {
        return this.registrationRepository.findByStudent_PcnAndRegistrationStatus(pcn, status);
    }

    public List<Registration> GetAllElectiveCoursesByPcnWithFilteredStatus(Integer pcn, RegistrationStatus registrationStatus) {
        return this.registrationRepository.findByStudent_PcnAndRegistrationStatusNot(pcn, registrationStatus);
    }
}
