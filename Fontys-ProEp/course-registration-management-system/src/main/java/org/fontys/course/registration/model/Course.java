package org.fontys.course.registration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Course {

    @Id
    @Column(nullable = false)
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer EC;

    @Column
    private Integer block;

    @Column
    private Integer maxSeats;

    @Column
    private Integer filledSeat;

    @Column
    private Date regStartDate;

    @Column
    private Date regEndDate;

    @ManyToMany
    @JoinTable(name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_code", referencedColumnName = "code"),
            inverseJoinColumns = @JoinColumn(name = "teacher_pcn", referencedColumnName = "pcn"))
    private List<Teacher> teachers;

    @JsonManagedReference(value = "course-courseState")
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseState> states;

    @JsonManagedReference(value = "course-review")
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Review> reviews;

    public Course() {
    }

    public Course(String code, String name, String description, Integer EC, Integer block, Integer maxSeats, Integer filledSeat, Date regStartDate, Date regEndDate) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.EC = EC;
        this.block = block;
        this.maxSeats = maxSeats;
        this.filledSeat = filledSeat;
        this.regStartDate = regStartDate;
        this.regEndDate = regEndDate;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getEC() {
		return EC;
	}

	public void setEC(Integer eC) {
		EC = eC;
	}

	public Integer getBlock() {
		return block;
	}

	public void setBlock(Integer block) {
		this.block = block;
	}

	public Integer getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(Integer maxSeats) {
		this.maxSeats = maxSeats;
	}

	public Integer getFilledSeat() {
		return filledSeat;
	}

	public void setFilledSeat(Integer filledSeat) {
		this.filledSeat = filledSeat;
	}

	public Date getRegStartDate() {
		return regStartDate;
	}

	public void setRegStartDate(Date regStartDate) {
		this.regStartDate = regStartDate;
	}

	public Date getRegEndDate() {
		return regEndDate;
	}

	public void setRegEndDate(Date regEndDate) {
		this.regEndDate = regEndDate;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<CourseState> getStates() {
		return states;
	}

	public void setStates(List<CourseState> states) {
		this.states = states;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
