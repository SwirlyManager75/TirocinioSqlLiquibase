package com.tirocinio.exceptions;

public class MuseoException extends Exception {
	
	private static final long serialVersionUID = -4180324526144609442L;
	
	private String message;
	
	public MuseoException(Throwable throwable) {
		super(throwable);
	}
	
	public MuseoException(String message, Throwable throwable) {
		super(throwable);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
