package org.fontys.course.registration.model;

import org.fontys.course.registration.model.enums.RegistrationStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(RegistrationId.class)
public class Registration {

//    @EmbeddedId
//    private RegistrationId id;

    @Id
    private Student student;
    @Id
    private Course course;

    @Column
    private Date date;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;

    public Registration() {
    }

    public Registration(Student student, Course course, Date date, RegistrationStatus registrationStatus) {
        this.student = student;
        this.course = course;
        this.date = date;
        this.registrationStatus = registrationStatus;
    }

    public Registration(int pcn, String courseCode, Date date, RegistrationStatus registrationStatus) {
        this.student = new Student();
        this.student.setPcn(pcn);
        this.course = new Course();
        this.course.setCode(courseCode);
        this.date = date;
        this.registrationStatus = registrationStatus;
    }

//    public RegistrationId getId() {
//        return id;
//    }

//    public void setId(RegistrationId id) {
//        this.id = id;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
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
