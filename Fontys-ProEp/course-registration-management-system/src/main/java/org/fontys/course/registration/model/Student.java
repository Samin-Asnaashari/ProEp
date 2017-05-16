package org.fontys.course.registration.model;

import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.model.enums.StudentType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Student")
public class Student extends Person {

    @Column(nullable = false)
    private Integer studentNumber;

    //TODO is it really necessary?
    @Enumerated(EnumType.STRING)
    private StudentType studentType;

    @Column
    @Enumerated(EnumType.STRING)
    private Major major;

    @Column
    private Double avgScore;

    @JsonManagedReference
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Notification> notifications;
    
    public Student() {
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
