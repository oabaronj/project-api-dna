package com.api.dna.excepciones;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RequestExceptions {
	
	ResponseExceptions responseException;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(RequestExceptions.class);
	
	@ExceptionHandler(Exception403.class)
	public ResponseEntity<ResponseExceptions> exception403(Exception403 e) {
		responseException = new ResponseExceptions(new Date(), 403, "Forbidden", e.getMessage());
		return new ResponseEntity<>(responseException, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(Exception422.class)
	public ResponseEntity<ResponseExceptions> exception422(Exception422 e) {
		responseException = new ResponseExceptions(new Date(), 422, "Unprocessable Entity", e.getMessage());
		return new ResponseEntity<>(responseException, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(Exception500.class)
	public ResponseEntity<ResponseExceptions> exception500(Exception500 e) {
		responseException = new ResponseExceptions(new Date(), 500, "Internal Server Error", e.getMessage());
		return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseExceptions> runtimeException(RuntimeException e) {
		responseException = new ResponseExceptions(new Date(), 500, "Internal Server Error", e.getMessage());
		return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseExceptions> exception(Exception e) {
		responseException = new ResponseExceptions(new Date(), 500, "Internal Server Error", e.getMessage());
		return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseExceptions> httpMessageNotReadableException(HttpMessageNotReadableException e){
		String mensaje = "Debe enviar la secuencia de ADN dentro de un arreglo [] en el campo 'dna'";
		LOGGER.error("Excepcion400: " + mensaje);
		responseException = new ResponseExceptions(new Date(), 400, "Bad Request", mensaje);
		return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
	}

}
