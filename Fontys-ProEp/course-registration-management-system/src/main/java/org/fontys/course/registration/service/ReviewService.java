package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Review;
import org.fontys.course.registration.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UtilService utilService;
	
	public List<Review> GetAllReviews(String courseCode) {
		try {
			return this.reviewRepository.findByCourseOrderByIdDesc(this.utilService.GetCourse(courseCode));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
