package com.api.dna.entidades;


import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
@ToString
@Entity
@Table(name = "TBL_DNA",
	   indexes = {@Index(name = "UK_DNA_01", columnList = "STR_CADENA_DNA", unique = true)})//schema = "apidna",

public class DnaEntity { 

	@Id
	@Column(name="NUM_ID_DNA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numIdDna;
	
	@Column(name="STR_CADENA_DNA", nullable=false, length=250)
	private String strCadenaDna;
	
	@Column(name="STR_ES_MUTANTE", nullable=false, length = 1)
	private String strEsMutante; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DTM_FECHA_CREACION", nullable=false)
	private Date dtmFechaCreacion;
	
	@Column(name="STR_ESTADO", nullable=false, length = 1)
	private String strEstado;
	
	@PrePersist
	public void prePersist() {
		this.dtmFechaCreacion = new Date();
		this.strEstado = "A";
	}
	
	public DnaEntity(){}
	
	public DnaEntity(String strCadenaDna, String strEsMutante){
		this.strCadenaDna = strCadenaDna;
		this.strEsMutante = strEsMutante;
	}
	
}
