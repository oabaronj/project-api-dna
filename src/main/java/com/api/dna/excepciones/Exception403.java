package com.api.dna.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Exception403 extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Exception403(String message) {
		super (message);
	}

}
