package org.fontys.course.registration.model;

public class PushNotification {
	private String message;
	private String title;
	
	public PushNotification() {
		super();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
