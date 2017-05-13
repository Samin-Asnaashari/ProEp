package org.fontys.course.registration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column
    private String content;

    @Column
    private Date date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action=OnDeleteAction.NO_ACTION)
    private Student student;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    public Review() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
