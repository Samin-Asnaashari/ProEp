package org.fontys.course.registration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.RegistrationId;
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
        Registration newRegistration = new Registration(new RegistrationId(utilService.GetStudentById(pcn), utilService.GetCourse(courseCode)), new Date(), RegistrationStatus.PENDING);
        this.registrationRepository.save(newRegistration);
    }

    @Transactional
    public void dropRegistration(String courseCode, Integer pcn) throws Exception {
        Registration reg =
                registrationRepository.findById(new RegistrationId
                        (utilService.GetStudentById(pcn),utilService.GetCourse(courseCode)));
        this.registrationRepository.delete(reg);
    }

    public List<Registration> GetAllRegistrations() {
        return this.registrationRepository.findAll();
    }

    @Transactional
    public List<Registration> GetAllRegistrationsByCourse(String courseCode) {
        try {
			return this.registrationRepository.findByIdCourse(this.utilService.GetCourse(courseCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

    @Transactional
    public List<Registration> GetAllRegistrationByStatusAndCourse(RegistrationStatus registrationStatus, String courseCode) throws Exception {
        return this.registrationRepository.findByRegistrationStatusAndIdCourse(registrationStatus, this.utilService.GetCourse(courseCode));
    }

	@Transactional
	public void UpdateRegistration(String courseCode, String studentPcn, String status) throws Exception {
		Registration registration = this.registrationRepository.findById(new RegistrationId(this.utilService.GetStudentById(studentPcn), this.utilService.GetCourse(courseCode)));
		registration.setRegistrationStatus(RegistrationStatus.valueOf(status));
	}

	@Transactional
    public List<Registration> GetAllRegistrationsByPcn(Integer studentPcn) {
        return this.registrationRepository.findByIdStudent(this.utilService.GetStudentById(studentPcn));
    }

	@Transactional
    public List<Course> GetAllAppliedElectiveCoursesByPcn(Integer studentPcn) {
        List<Registration> registrations = this.registrationRepository.findByIdStudent(this.utilService.GetStudentById(studentPcn));
        List<Course> courses = new ArrayList<>();

        for (Registration reg : registrations)
            courses.add(reg.getId().getCourse());

        return courses;
    }

	@Transactional
    public List<Registration> GetAllRegistrationsByPcnWithStatus(Integer studentPcn, RegistrationStatus status) {
        return this.registrationRepository.findByIdStudentAndRegistrationStatus(this.utilService.GetStudentById(studentPcn), status);
    }

	@Transactional
    public List<Registration> GetAllElectiveCoursesByPcnWithFilteredStatus(Integer studentPcn, RegistrationStatus registrationStatus) {
        return this.registrationRepository.findByIdStudentAndRegistrationStatusNot(this.utilService.GetStudentById(studentPcn), registrationStatus);
    }
}
