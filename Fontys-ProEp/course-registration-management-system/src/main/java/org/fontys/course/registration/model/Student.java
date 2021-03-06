package org.fontys.course.registration.model;

import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.model.enums.StudentType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import javax.persistence.*;

@Entity
public class Student extends Person {

    @Column(nullable = false)
    private Integer studentNumber;

    @Enumerated(EnumType.STRING)
    private StudentType studentType;

    @Column
    @Enumerated(EnumType.STRING)
    private Major major;

    @Column
    private Double avgScore;
    
    @Column
    private String pushNotificationToken;
    
    @Column
    private Integer notificationBadgeCount;

	@JsonManagedReference
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.REMOVE)
    private List<Notification> notifications;
    
    public Student() {
    }

    public Student(Integer pcn, String password, String email, String firstName, String lastName, Integer studentNumber, StudentType studentType, Major major, Double avgScore) {
        super(pcn, password, email, firstName, lastName);
        this.studentNumber = studentNumber;
        this.studentType = studentType;
        this.major = major;
        this.avgScore = avgScore;
    }

    public Integer getNotificationBadgeCount() {
		return notificationBadgeCount;
	}

	public void setNotificationBadgeCount(Integer notificationBadgeCount) {
		this.notificationBadgeCount = notificationBadgeCount;
	}
    
    public String getPushNotificationToken() {
		return pushNotificationToken;
	}

	public void setPushNotificationToken(String pushNotificationToken) {
		this.pushNotificationToken = pushNotificationToken;
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
