package br.leosilvadev.gchat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NoAuthenticatedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3309174627129915516L;

	public NoAuthenticatedException(String message){
		super(message);
	}

	public NoAuthenticatedException(String message, Throwable cause){
		super(message, cause);
	}
	
}
