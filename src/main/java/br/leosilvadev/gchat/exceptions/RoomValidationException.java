package br.leosilvadev.gchat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoomValidationException extends RuntimeException {

	private static final long serialVersionUID = 1999668666831033401L;

}
