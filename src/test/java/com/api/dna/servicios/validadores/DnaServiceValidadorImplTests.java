package com.api.dna.servicios.validadores;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Assertions;
import com.api.dna.dto.DnaRequestDTO;
import com.api.dna.excepciones.Exception403;
import com.api.dna.excepciones.Exception422;
import com.api.dna.excepciones.Exception500;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DnaServiceValidadorImplTests {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(DnaServiceValidadorImplTests.class);
	
	@Autowired
	private DnaServiceValidadorLocal validador;
	
	@Test
	public void validarEsVacioTest() {
		
		LOGGER.info("Entro a metodo validarEsVacioTest()");
		
		DnaRequestDTO dnaRequestDTO = new DnaRequestDTO();
		dnaRequestDTO.setNumIdDna(1);
		dnaRequestDTO.setStrCadenaDna(new ArrayList<String>());
		dnaRequestDTO.setDtmFechaCreacion(new Date());
		dnaRequestDTO.setStrEsMutante("S");
		dnaRequestDTO.setStrEstado("A");
		dnaRequestDTO.toString();

		dnaRequestDTO.getStrCadenaDna().add("ATGCGA");
		dnaRequestDTO.getStrCadenaDna().add("ATGCGA");
		
		Assertions.assertDoesNotThrow(() -> validador.validarEsVacio(dnaRequestDTO));		
	}
	
	@Test
	public void validarEsVacioExcepcionTest() throws Exception500 {
		
		LOGGER.info("Entro a metodo validarEsVacioExcepcionTest()");
		
		DnaRequestDTO dnaRequestDTO = new DnaRequestDTO();
		dnaRequestDTO.setStrCadenaDna(new ArrayList<String>());
		
		Assertions.assertThrows(Exception500.class, () -> validador.validarEsVacio(null));
		Assertions.assertThrows(Exception500.class, () -> validador.validarEsVacio(new DnaRequestDTO()));
		Assertions.assertThrows(Exception500.class, () -> validador.validarEsVacio(dnaRequestDTO));
		
		dnaRequestDTO.getStrCadenaDna().add(null);
		Assertions.assertThrows(Exception500.class, () -> validador.validarEsVacio(dnaRequestDTO));		
	}
	
	@Test
	public void validarLongitudSecuenciaTest() {
		
		LOGGER.info("Entro a metodo validarLongitudSecuenciaTest()");
		
		DnaRequestDTO dnaRequestDTO = new DnaRequestDTO();
		dnaRequestDTO.setStrCadenaDna(new ArrayList<String>());
		
		dnaRequestDTO.getStrCadenaDna().add("ATGCGA");
		dnaRequestDTO.getStrCadenaDna().add("CAGTGC");
		dnaRequestDTO.getStrCadenaDna().add("TCATGT");
		dnaRequestDTO.getStrCadenaDna().add("AGCAGG");
		dnaRequestDTO.getStrCadenaDna().add("CCCCTA");
		dnaRequestDTO.getStrCadenaDna().add("TCACTG");
		
		Assertions.assertDoesNotThrow(() -> validador.validarLongitudSecuencia(dnaRequestDTO));		
	}
	
	@Test
	public void validarLongitudSecuenciaExcepcionTest() throws Exception422 {
		
		LOGGER.info("Entro a metodo validarLongitudSecuenciaExcepcionTest()");
		
		DnaRequestDTO dnaRequestDTO = new DnaRequestDTO();
		dnaRequestDTO.setStrCadenaDna(new ArrayList<String>());
		
		dnaRequestDTO.getStrCadenaDna().add("ATGCGA");
		dnaRequestDTO.getStrCadenaDna().add("CAGTGC");
		
		Assertions.assertThrows(Exception422.class, () -> validador.validarLongitudSecuencia(dnaRequestDTO));				
	}
		
	@Test
	public void validarCaracteresPermitidosTest() {
		
		LOGGER.info("Entro a metodo validarCaracteresPermitidosTest()");
		
		DnaRequestDTO dnaRequestDTO = new DnaRequestDTO();
		dnaRequestDTO.setStrCadenaDna(new ArrayList<String>());
		
		dnaRequestDTO.getStrCadenaDna().add("ATGCGA");
		dnaRequestDTO.getStrCadenaDna().add("CAGTGC");
		
		Assertions.assertDoesNotThrow(() -> validador.validarCaracteresPermitidos(dnaRequestDTO));		
	}
	
	@Test
	public void validarCaracteresPermitidosExcepcionTest() throws Exception422 {
		
		LOGGER.info("Entro a metodo validarCaracteresPermitidosExcepcionTest()");
		
		DnaRequestDTO dnaRequestDTO = new DnaRequestDTO();
		dnaRequestDTO.setStrCadenaDna(new ArrayList<String>());
		
		dnaRequestDTO.getStrCadenaDna().add("XTGI*A");
		dnaRequestDTO.getStrCadenaDna().add("C G+GC");

		Assertions.assertThrows(Exception422.class, () -> validador.validarCaracteresPermitidos(dnaRequestDTO));				
	}
	
	@Test
	public void isMutantTest() throws Exception403 {
		
		LOGGER.info("Entro a metodo  isMutantTest()");
		
		List<String> listaStrCadenaDna = new ArrayList<String>();
		listaStrCadenaDna.add("ATGGGA");
		listaStrCadenaDna.add("GAGGGC");
		listaStrCadenaDna.add("TGACGG");
		listaStrCadenaDna.add("GGGAGG");
		listaStrCadenaDna.add("CCCGTA");
		listaStrCadenaDna.add("GGGGTG");
		
		assertEquals(true, validador.isMutant(listaStrCadenaDna));
	}
	
	@Test
	public void isNotMutantTest() throws Exception403 {
		
		LOGGER.info("Entro a metodo isNotMutantTest()");
		
		List<String> listaStrCadenaDna = new ArrayList<String>();
		listaStrCadenaDna.add("ATGCAA");
		listaStrCadenaDna.add("CAGTGC");
		listaStrCadenaDna.add("TTATGT");
		listaStrCadenaDna.add("AGAAGG");
		listaStrCadenaDna.add("CCCATA");
		listaStrCadenaDna.add("TCACTG");
		
		assertEquals(false, validador.isMutant(listaStrCadenaDna));
	}
}
