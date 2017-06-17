package org.fontys.course.registration.repository;

import java.util.List;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Registration;
import org.fontys.course.registration.model.RegistrationId;
import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {
	
    List<Registration> findByIdCourse(Course course);
    List<Registration> findByRegistrationStatusAndIdCourse(RegistrationStatus status, Course course);
    Registration findById(RegistrationId id);
    List<Registration> findByIdStudent(Student student);
    List<Registration> findByIdStudentAndRegistrationStatus(Student student, RegistrationStatus status);
    List<Registration> findByIdStudentAndRegistrationStatusNot(Student student, RegistrationStatus status);
    Long deleteByIdStudent(Student student);
    Long deleteByIdCourse(Course course);
}
