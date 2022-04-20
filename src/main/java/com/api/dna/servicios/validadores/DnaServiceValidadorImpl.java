package com.api.dna.servicios.validadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.api.dna.dto.DnaRequestDTO;
import com.api.dna.excepciones.Exception422;
import com.api.dna.excepciones.Exception500;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DnaServiceValidadorImpl implements DnaServiceValidadorLocal{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(DnaServiceValidadorImpl.class);
	
	@Override
	public DnaRequestDTO validarNombreCampoDna(HashMap<String, List<String>> json) throws Exception422{
		
		LOGGER.info("Entro a metodo validarNombreCampoDna()");
				
		if(!json.containsKey("dna")) {
			LOGGER.error("Excepcion422: Nombre del campo o variable enviado es incorrecto, el campo o variable a enviar se debe llamar 'dna' en minuscula. Solo se permite enviar como parametro una secuencia de DNA por medio de un arreglo []");
			throw new Exception422("Nombre del campo o variable enviado es incorrecto, el campo o variable a enviar se debe llamar 'dna' en minuscula. Solo se permite enviar como parametro una secuencia de DNA por medio de un arreglo []");
		}
		
		DnaRequestDTO dnaRequestDTO = new DnaRequestDTO(json.get("dna"));
		
		LOGGER.info(dnaRequestDTO.toString());
		return dnaRequestDTO;
	}
	
	@Override
	public void validarEsVacio(DnaRequestDTO dnaRequestDTO) throws Exception500 {

		LOGGER.info("Entro a metodo validarEsVacio()");
		
		if(dnaRequestDTO == null || dnaRequestDTO.getStrCadenaDna() == null || dnaRequestDTO.getStrCadenaDna().isEmpty()) {
			LOGGER.error("Excepcion500: Secuencia de caracteres no debe estar vacia o null.");
			throw new Exception500("Secuencia de caracteres no debe estar vacia o null.");
		}
				
		for(int i = 0; i < dnaRequestDTO.getStrCadenaDna().size();i++) {
			if(dnaRequestDTO.getStrCadenaDna().get(i) == null || dnaRequestDTO.getStrCadenaDna().get(i).isEmpty()) {
				LOGGER.error("Excepcion500: No debe enviar segmentos de caracteres vacios o null. Segmento numero " + (i+1) + " esta vacio o null.");
				throw new Exception500("No debe enviar segmentos de caracteres vacios o null. Segmento numero " + (i+1) + " esta vacio o null.");
			}
		}	
	} 
	
	@Override
	public void validarLongitudSecuencia(DnaRequestDTO dnaRequestDTO) throws Exception422 {

		LOGGER.info("Entro a metodo validarLongitudSecuencia()");
		
		for(int i = 0; i < dnaRequestDTO.getStrCadenaDna().size();i++) {
			List<String> listaCaracteresSecuenciaDna = Arrays.asList(dnaRequestDTO.getStrCadenaDna().get(i).split(""));
			if(dnaRequestDTO.getStrCadenaDna().size() != listaCaracteresSecuenciaDna.size()) {
				LOGGER.error("Excepcion422: El numero de caracteres de cada segmento debe ser igual al numero total de secuencias dentro del arreglo, por ejemplo una secuencia de 4 segmentos por 4 caracteres cada una: [1234,1234,1234,1234], Segmento numero " + (i+1) + " esta incompleto");
				throw new Exception422("El numero de caracteres de cada segmento debe ser igual al numero total de secuencias dentro del arreglo, por ejemplo una secuencia de 4 segmentos por 4 caracteres cada una: [1234,1234,1234,1234], Segmento numero " + (i+1) + " esta incompleto");
			}
		}	
	}
	
	@Override
	public void validarCaracteresPermitidos(DnaRequestDTO dnaRequestDTO) throws Exception422 {

		LOGGER.info("Entro a metodo validarCaracteresPermitidos()");
		
		String caracteresPermitidos = ("ATCG");
		
		for(int i = 0; i < dnaRequestDTO.getStrCadenaDna().size();i++) {
			List<String> listaCaracteresSecuenciaDna = Arrays.asList(dnaRequestDTO.getStrCadenaDna().get(i).split(""));
			for(int j = 0; j < listaCaracteresSecuenciaDna.size(); j++){
				if(!caracteresPermitidos.contains(listaCaracteresSecuenciaDna.get(j))) {
					LOGGER.error("Caracteres enviados en el segmento numero " + (i+1) + " no son permitidos, los caracteres permitidos son: (" + caracteresPermitidos + ")");
					throw new Exception422("Caracteres enviados en el segmento numero " + (i+1) + " no son permitidos, los caracteres permitidos son: (" + caracteresPermitidos + ")");
				}
			}
		}
	}
	
	@Override
	public boolean isMutant(List<String> listaSecuenciaDna) {
		
		LOGGER.info("Entro a metodo isMutant()");
		boolean esMutante = true;
		List<List<String>> listaCaracteresDna = crearListaDeCaracteresDna(listaSecuenciaDna);
		
		int totalSecuenciasMutantes = buscarSecuenciasHorizontales(listaCaracteresDna);
		totalSecuenciasMutantes += buscarSecuenciasVerticales(listaCaracteresDna);
		totalSecuenciasMutantes += buscarSecuenciasOblicuas(listaCaracteresDna);
		
		LOGGER.info("Total Secuencias Mutantes: [" + totalSecuenciasMutantes + "]");
		
		if(totalSecuenciasMutantes<2) {
			esMutante = false;
		}
		LOGGER.info("esMutante: [" + esMutante + "]");
		return esMutante;
	}
	
	public List<List<String>> crearListaDeCaracteresDna(List<String> listaSecuenciaDna) {		
		List<List<String>> listaCaracteresDna = new ArrayList<List<String>>();
		for (int i=0; i<listaSecuenciaDna.size(); i++){
			listaCaracteresDna.add(Arrays.asList(listaSecuenciaDna.get(i).split("")));
        }
		return listaCaracteresDna;
	}
	
	public int buscarSecuenciasHorizontales(List<List<String>> listaCaracteresDna) {
		int totalSecuenciasMutantes = 0;
		for(int i = 0; i < listaCaracteresDna.size(); i++) {
			if(buscarSecuenciaMutante(listaCaracteresDna.get(i), listaCaracteresDna.get(i).toString() + " Horizontal")) {
				totalSecuenciasMutantes++;
			}
		}
		return totalSecuenciasMutantes;
	}
	
	public int buscarSecuenciasVerticales(List<List<String>> listaCaracteresDna) {
		int totalSecuenciasMutantes = 0;
		for (int i=0; i<listaCaracteresDna.size(); i++){
			List<String> listaCaracteresVerticales = new ArrayList<String>();
			for(int j=0; j<listaCaracteresDna.size(); j++) {
				listaCaracteresVerticales.add(listaCaracteresDna.get(j).get(i));
			}
			if(buscarSecuenciaMutante(listaCaracteresVerticales, listaCaracteresVerticales.toString() + " Vertical")) {
				totalSecuenciasMutantes++;
			}
        }       
		return totalSecuenciasMutantes;
	}
	
	public int buscarSecuenciasOblicuas(List<List<String>> listaCaracteresDna) {
		
		int totalSecuenciasMutantes = 0;		
        
		for (int i = 0; i < listaCaracteresDna.size(); i++) {
        	
			int contador = 0;
			List<String> listaCaracteresOblicuosUno = new ArrayList<String>();
			List<String> listaCaracteresOblicuosDos = new ArrayList<String>();
			
            for(int j = 0; j < listaCaracteresDna.get(i).size(); j++) {
            	if((i+j) < listaCaracteresDna.size()) {	            	
            		listaCaracteresOblicuosUno.add(listaCaracteresDna.get(j).get(j+i));            	
            	}
            	if((i + j) < (listaCaracteresDna.size() - 1)) {
            		listaCaracteresOblicuosDos.add(listaCaracteresDna.get(j+i+1).get(j));           	
            	}
            }
            
            if(buscarSecuenciaMutante(listaCaracteresOblicuosUno, listaCaracteresOblicuosUno.toString() + " Oblicua 1")) {
				totalSecuenciasMutantes++;
			}
            if(buscarSecuenciaMutante(listaCaracteresOblicuosDos, listaCaracteresOblicuosDos.toString() + " Oblicua 2")) {
				totalSecuenciasMutantes++;
			}
            
            listaCaracteresOblicuosUno.clear();
            listaCaracteresOblicuosDos.clear();                            
                        
            for(int j = listaCaracteresDna.get(i).size()-1; j >= 0; j--) {            	
            	if((i + contador) < listaCaracteresDna.size()) {
            		listaCaracteresOblicuosUno.add(listaCaracteresDna.get(contador+i).get(j));            	
            	}
            	if((i + contador) < (listaCaracteresDna.size() - 1)) {
            		listaCaracteresOblicuosDos.add(listaCaracteresDna.get(contador).get(j-i-1));            	
            	}
	            contador++;
            }
            
            if(buscarSecuenciaMutante(listaCaracteresOblicuosUno, listaCaracteresOblicuosUno.toString() + " Oblicua 3")) {
				totalSecuenciasMutantes++;
			}
            if(buscarSecuenciaMutante(listaCaracteresOblicuosDos, listaCaracteresOblicuosDos.toString() + " Oblicua 4")) {
				totalSecuenciasMutantes++;
			} 
        }       
		return totalSecuenciasMutantes;
	}
	
	public boolean buscarSecuenciaMutante(List<String> listaSecuenciaDna, String secuenciaDna) {
		int contadorSecuencia = 1;
		boolean secuenciaEsValida = false;
		for(int i=0; i<listaSecuenciaDna.size();i++) {
			if(i < (listaSecuenciaDna.size()-1)) {
				if(listaSecuenciaDna.get(i).equals(listaSecuenciaDna.get(i+1))){
					contadorSecuencia++;
				}else {
					contadorSecuencia=1;
				}
				if(contadorSecuencia == 4) {
					secuenciaEsValida = true;
					break;
				}
			}
		}
		//LOGGER.info("secuenciaEsValida: " + secuenciaEsValida + " " + secuenciaDna);
		return secuenciaEsValida;
	}

}
