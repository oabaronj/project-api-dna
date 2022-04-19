package com.api.dna.servicios.validadores;

import java.util.List;
import java.util.HashMap;
import com.api.dna.dto.DnaRequestDTO;
import com.api.dna.excepciones.Excepcion403;
import com.api.dna.excepciones.Excepcion422;
import com.api.dna.excepciones.Excepcion500;
import org.springframework.stereotype.Service;

@Service
public interface DnaServiceValidadorLocal {
	
	DnaRequestDTO validarNombreCampoDna(HashMap<String, List<String>> json) throws Excepcion422;
	
	void validarEsVacio(DnaRequestDTO dnaRequestDTO) throws Excepcion500;
	
	void validarLongitudSecuencia(DnaRequestDTO usuariosRequestDTO) throws Excepcion422;
	
	void validarCaracteresPermitidos(DnaRequestDTO dnaRequestDTO) throws Excepcion422;
	
	boolean isMutant(List<String> listaSecuenciaDna) throws Excepcion403;

}
