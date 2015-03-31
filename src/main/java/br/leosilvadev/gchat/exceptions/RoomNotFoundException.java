package br.leosilvadev.gchat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoomNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8892095096982984272L;

}
