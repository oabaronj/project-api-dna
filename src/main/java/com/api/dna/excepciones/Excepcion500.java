package com.api.dna.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Excepcion500  extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Excepcion500(String message) {
		super (message);
	}

}
