package org.fontys.course.registration.service;

import java.util.List;

import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void AddCourse(Course course) {
        this.courseRepository.save(course);
    }

    public Course GetCourse(String id) {
        return this.courseRepository.findOne(id);
    }

    public List<Course> GetAllCourses() {
        return this.courseRepository.findAll();
    }

    @Transactional
    public void UpdateCourse(Course course) {
        this.courseRepository.save(course);
    }

    @Transactional
    public void DeleteCourse(String id) {
        this.courseRepository.delete(id);
    }
}
