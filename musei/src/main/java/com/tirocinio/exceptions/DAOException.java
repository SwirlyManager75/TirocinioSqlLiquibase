package com.tirocinio.exceptions;

public class DAOException extends MuseoException {
	
    private static final long serialVersionUID = 8687809297203452896L;

	public DAOException(String message, Throwable cause) {
    	super(message, cause);
    }
}
