package br.leosilvadev.gchat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7139502423232055657L;

	public InvalidTokenException(String message){
		super(message);
	}

	public InvalidTokenException(String message, Throwable cause){
		super(message, cause);
	}
	
}
