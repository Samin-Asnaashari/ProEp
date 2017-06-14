package org.fontys.course.registration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

//@Embeddable
public class RegistrationId implements Serializable {

    //    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    @JoinColumn
    private Student student;

    //    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    @JoinColumn
    private Course course;

    public RegistrationId() {
    }

    public RegistrationId(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
