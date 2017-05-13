package org.fontys.course.registration.exception;

public class Message {

	//private Integer code;
	private String message;
	public Message(String message) {
		super();
		//this.code = code;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
