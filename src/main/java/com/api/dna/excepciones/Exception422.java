package com.api.dna.excepciones;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class Exception422 extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Exception422(String message) {
		super (message);
	}

}
