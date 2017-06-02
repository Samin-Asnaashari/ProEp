package org.fontys.course.registration.model;

import javax.persistence.*;

import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.model.enums.NotificationStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    private String content;

    @Column
    private Date date;
    
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn
    private Person sender;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private Person receiver;

    public Notification(String content, Date date, Person sender, Person receiver) {
		super();
		this.content = content;
		this.date = date;
		this.sender = sender;
		this.receiver = receiver;
	}

	public Notification() {
    }

    public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }
}
