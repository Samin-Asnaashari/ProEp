package org.fontys.course.registration.repository;

import java.util.List;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

	List<Review> findByCourseOrderByIdDesc(Course course);
}