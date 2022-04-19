package com.api.dna.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Excepcion403 extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Excepcion403(String message) {
		super (message);
	}

}
