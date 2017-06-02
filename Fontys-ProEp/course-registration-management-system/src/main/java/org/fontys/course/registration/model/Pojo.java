package org.fontys.course.registration.model;

public class Pojo {
	private String tokens;
	private String profile;
	private PushNotification notification;
	
	public Pojo() {
		super();
	}
	public String getTokens() {
		return tokens;
	}
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public PushNotification getNotification() {
		return notification;
	}
	public void setNotification(PushNotification notification) {
		this.notification = notification;
	}
	
}
