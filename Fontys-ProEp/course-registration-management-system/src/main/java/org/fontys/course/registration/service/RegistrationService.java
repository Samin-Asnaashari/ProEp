package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.fontys.course.registration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

	@Autowired
    private RegistrationRepository registrationRepository;
	
	public List<Registration> GetAllRegistrations() {
		return this.registrationRepository.findAll();
	}
	
	public List<Registration> GetAllRegistrationsByCourse(String courseCode){
		return this.registrationRepository.findRegistrationByCourse(courseCode);
	}

	public List<Registration> GetAllRegistrationByStatus(RegistrationStatus registrationStatus){
		return this.registrationRepository.findByRegistrationStatus(registrationStatus);
	}
}
