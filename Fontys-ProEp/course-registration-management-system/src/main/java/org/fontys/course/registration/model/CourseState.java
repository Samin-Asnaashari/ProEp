package org.fontys.course.registration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.fontys.course.registration.model.enums.CourseType;
import org.fontys.course.registration.model.enums.Major;

import javax.persistence.*;

public class CourseState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column
    private Major major;

    @Column
    private CourseType courseType;

    //TODO check.
    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    public CourseState() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
