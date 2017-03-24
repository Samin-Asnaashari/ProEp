package org.fontys.course.registration.model;

import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.model.enums.StudentType;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Student")
public class Student extends Person {

    @Column(nullable = false)
    private Integer StudentNumber;

    //TODO is it really necessary?
    @Column
    private StudentType studentType;

    @Column
    private Major major;

    @Column
    private Double avgScore;

    public Student() {
    }

    public Integer getStudentNumber() {
        return StudentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        StudentNumber = studentNumber;
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
}
