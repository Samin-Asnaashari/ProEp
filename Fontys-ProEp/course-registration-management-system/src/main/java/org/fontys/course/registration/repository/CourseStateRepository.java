package org.fontys.course.registration.repository;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.CourseState;
import org.fontys.course.registration.model.enums.CourseType;
import org.fontys.course.registration.model.enums.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStateRepository extends JpaRepository<CourseState, Integer> {
    List<CourseState> findByMajorAndCourseType(Major major, CourseType courseType);
}
