package org.fontys.course.registration.repository;

import org.fontys.course.registration.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    //TODO be consistent use JPA repository has more priority
    //Replace your code and Query with :  Course findByCode(String courseCode);
    @Query(value = "SELECT * FROM Course WHERE code = ?1", nativeQuery = true)
    Course findCourse(String courseCode);

    List<Course> findByTeachersPcn(Integer pcn);
}


