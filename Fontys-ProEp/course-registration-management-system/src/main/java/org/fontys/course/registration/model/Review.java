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
    private Long id;

    @Column
    private String description;
    
    @Column
    private String negativePoints;
    
    @Column
    private String positivePoints;

    @Column
    private Date date;

    @Column
    private Integer score;
    
    @OneToOne
    @JoinColumn(referencedColumnName="pcn")
    private Student student;

    @JsonBackReference(value = "course-review")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    public Review() {
    }

    public Review(String description,String negativePoints,String positivePoints, Integer score )
	{
		this.description=description;
		this.negativePoints=negativePoints;
		this.positivePoints=positivePoints;
		this.score=score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNegativePoints() {
		return negativePoints;
	}

	public void setNegativePoints(String negativePoints) {
		this.negativePoints = negativePoints;
	}

	public String getPositivePoints() {
		return positivePoints;
	}

	public void setPositivePoints(String positivePoints) {
		this.positivePoints = positivePoints;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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
