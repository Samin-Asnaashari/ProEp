package org.fontys.course.registration.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.Review;
import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UtilService utilService;
	
	@Transactional
	public List<Review> GetAllReviews(String courseCode) {
		try {
			return this.reviewRepository.findByCourseOrderByIdDesc(this.utilService.GetCourse(courseCode));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void AddReview(Integer pcn, Review review, String courseCode) {
		//TODO Security, make sure this student does not already have a review for this course
		Course course = null;
		Student student = null;
		try {
			course = this.utilService.GetCourse(courseCode);
			student = this.utilService.GetStudentById(pcn.toString());
			review.setCourse(course);
			review.setStudent(student);
			review.setDate(new Date());
			this.reviewRepository.save(review);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
