package com.restapi.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticateException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public InvalidJwtAuthenticateException(String exception) {
		super(exception);
	}
	
}
