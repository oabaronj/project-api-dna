package com.api.dna.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DnaResponseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("dna")
	private String strCadenaDna;
	@JsonProperty("esMutante")
	private String strEsMutante;
	@JsonProperty("estado")
	private String strEstado;

}
