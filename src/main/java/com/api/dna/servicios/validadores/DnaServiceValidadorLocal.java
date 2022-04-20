package com.api.dna.servicios.validadores;

import java.util.List;
import java.util.HashMap;
import com.api.dna.dto.DnaRequestDTO;
import com.api.dna.excepciones.Exception403;
import com.api.dna.excepciones.Exception422;
import com.api.dna.excepciones.Exception500;
import org.springframework.stereotype.Service;

@Service
public interface DnaServiceValidadorLocal {
	
	DnaRequestDTO validarNombreCampoDna(HashMap<String, List<String>> json) throws Exception;
	
	void validarEsVacio(DnaRequestDTO dnaRequestDTO) throws Exception500;
	
	void validarLongitudSecuencia(DnaRequestDTO usuariosRequestDTO) throws Exception422;
	
	void validarCaracteresPermitidos(DnaRequestDTO dnaRequestDTO) throws Exception422;
	
	boolean isMutant(List<String> listaSecuenciaDna) throws Exception403;

}
