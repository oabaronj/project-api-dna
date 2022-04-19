package com.api.dna.excepciones;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Excepcion400 {

	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	private String trace;

}
