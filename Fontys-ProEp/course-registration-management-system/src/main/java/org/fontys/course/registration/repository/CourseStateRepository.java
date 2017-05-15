package org.fontys.course.registration.repository;

import org.fontys.course.registration.model.CourseState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStateRepository extends JpaRepository<CourseState, Integer> {
}
