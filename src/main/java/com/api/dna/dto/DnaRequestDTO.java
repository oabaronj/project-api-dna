package com.api.dna.dto;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class DnaRequestDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer numIdDna;
	
	private List<String> strCadenaDna;
	
	private String strEsMutante = "S";
	
	private Date dtmFechaCreacion;
	
	private String strEstado;
	
	public DnaRequestDTO() {}
	
	public DnaRequestDTO(List<String> strCadenaDna) {
		this.strCadenaDna = strCadenaDna;
	}

}
