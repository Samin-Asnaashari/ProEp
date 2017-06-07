package org.fontys.course.registration.controller;

import java.util.List;

import org.fontys.course.registration.model.Review;
import org.fontys.course.registration.service.ReviewService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/reviews")
@Api(
        name = "Review model API",
        description = "provides the list of methods that manages the reviews.",
        stage = ApiStage.RC
)
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value = "/{courseCode}", method = RequestMethod.GET)
    public List<Review> GetAllReviews(@PathVariable("courseCode") String courseCode) {
        return this.reviewService.GetAllReviews(courseCode);
    }
}
