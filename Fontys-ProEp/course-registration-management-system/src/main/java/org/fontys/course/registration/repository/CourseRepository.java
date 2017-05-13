package org.fontys.course.registration.repository;

import org.fontys.course.registration.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
	
	@Query(value = "SELECT * FROM Course WHERE code = ?1", nativeQuery = true)
	Course findCourse(String courseCode);
}


