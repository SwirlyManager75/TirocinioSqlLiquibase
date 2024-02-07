package com.tirocinio.exceptions;

public class ServiceException extends MuseoException {
    
	public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public ServiceException(MuseoException customException) {
		super(customException.getCause());
		this.setMessage(customException.getMessage());
	}
	
}
