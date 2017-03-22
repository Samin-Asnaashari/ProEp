package org.fontys.course.registration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class RegistrationId  implements Serializable {

//    @Id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

//    @Id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    public RegistrationId() {
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
