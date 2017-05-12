package org.fontys.course.registration.repository;

import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.RegistrationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {
	
}
