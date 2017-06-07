package org.fontys.course.registration.repository;

import java.util.List;

import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.RegistrationId;
import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {

    //TODO be consistent use JPA repository has more priority
    //Replace your code and Query with : List<Registration> findByCourseCode(String courseCode);
    @Query(value = "SELECT * FROM Registration WHERE course_code = ?1", nativeQuery = true)
    List<Registration> findRegistrationByCourse(String courseCode);

    List<Registration> findByRegistrationStatus(RegistrationStatus status);
}
