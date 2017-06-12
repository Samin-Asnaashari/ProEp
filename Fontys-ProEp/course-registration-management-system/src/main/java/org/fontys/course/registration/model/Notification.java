package org.fontys.course.registration.model;

import javax.persistence.*;

import org.fontys.course.registration.model.enums.NotificationStatus;
import org.fontys.course.registration.model.enums.SendStatus;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
//    @JsonFormat
//    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd hh:mm:ss")
    private Date date;
    
	@Enumerated(EnumType.STRING)
    private NotificationStatus status;
    
    @Enumerated(EnumType.STRING)
    private SendStatus sendStatus;

    @JsonIgnoreProperties("myCourses")
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
		this.sendStatus = SendStatus.UNSEND;
		this.status = NotificationStatus.UNREAD;
	}
	
	public Notification() {
    }

    public SendStatus getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(SendStatus sendStatus) {
		this.sendStatus = sendStatus;
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
