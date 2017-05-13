package org.fontys.course.registration.repository;

import java.util.List;

import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.RegistrationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {
	
	@Query(value = "SELECT * FROM Registration WHERE course_code = ?1", nativeQuery = true)
	List<Registration> findRegistrationByCourse(String courseCode);
}
