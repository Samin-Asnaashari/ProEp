package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService  {

    @Autowired
    private CourseRepository courseRepository;

	public List<Course> GetAllCourses() {
		return this.courseRepository.findAll();
	}

	public Course GetCourse(String id) {
		return this.courseRepository.findOne(id);
	}

	public void AddCourse(Course course) {
		this.courseRepository.save(course);
	}

	public void UpdateCourse(Course course) {
		this.courseRepository.save(course);
	}

	public void DeleteCourse(String id) {
		this.courseRepository.delete(id);
	}

    //TODO this is example.
//    @Transactional //only when is needed
//    @Override
//    public Block create(Block block) throws Exception {
//        Style style = new Style();
//        style = this.styleService.create(style);
//        block.setStyle(style);
//        return this.blockRepository.save(block);
//    }
}
