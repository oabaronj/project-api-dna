package com.api.dna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@ToString
@AllArgsConstructor

public class DnaResponseStatsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("count_mutant_dna")
	private BigDecimal strCountMutantDna;
	@JsonProperty("count_human_dna")
	private BigDecimal strCountHumanDna;
	@JsonProperty("ratio")
	private String strRatio;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DnaResponseStatsDTO other = (DnaResponseStatsDTO) obj;
		return Objects.equals(strCountHumanDna, other.strCountHumanDna)
				&& Objects.equals(strCountMutantDna, other.strCountMutantDna)
				&& Objects.equals(strRatio, other.strRatio);
	}

	
}
