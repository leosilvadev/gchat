package br.leosilvadev.gchat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class AuthTokenGenerationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3578262265655086241L;

	public AuthTokenGenerationException(String message){
		super(message);
	}
	
	public AuthTokenGenerationException(String message, Throwable cause){
		super(message, cause);
	}
	
}
