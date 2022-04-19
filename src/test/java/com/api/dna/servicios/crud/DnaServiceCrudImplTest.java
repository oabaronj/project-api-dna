package com.api.dna.servicios.crud;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Assertions;
import com.api.dna.dto.DnaRequestDTO;
import com.api.dna.dto.DnaResponseStatsDTO;
import com.api.dna.entidades.DnaEntity;
import com.api.dna.excepciones.Excepcion403;
import com.api.dna.repositorios.DnaRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class DnaServiceCrudImplTest {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(DnaServiceCrudImplTest.class);
	
	@MockBean
	private DnaRepository dnaRepositoryMock;
	
	@Autowired 
	private DnaServiceCrudLocal crud;
		
	@Test
	public void saveDna_NoSiExisteTest() {
		
		LOGGER.info("Entro a metodo saveDna_NoSiExisteTest()");
		
		DnaRequestDTO dnaRequestDTO = new DnaRequestDTO();
		dnaRequestDTO.setStrCadenaDna(new ArrayList<String>());
		
		dnaRequestDTO.getStrCadenaDna().add("ATGGGA");
		dnaRequestDTO.getStrCadenaDna().add("GAGGGC");
		dnaRequestDTO.getStrCadenaDna().add("TGACGG");
		dnaRequestDTO.getStrCadenaDna().add("GGGAGG");
		dnaRequestDTO.getStrCadenaDna().add("CCCGTA");
		dnaRequestDTO.getStrCadenaDna().add("GGGGTG");		

		when(this.dnaRepositoryMock.existsByStrCadenaDna(dnaRequestDTO.getStrCadenaDna().toString())).thenReturn(true);
		Assertions.assertDoesNotThrow(() -> crud.saveDna(dnaRequestDTO, true));
		Assertions.assertThrows(Excepcion403.class,() -> crud.saveDna(dnaRequestDTO, false));
	}	
	
	@Test
	public void saveDna_SiNoExisteTest() {
		
		LOGGER.info("Entro a metodo saveDna_SiNoExisteTest()");
		
		DnaRequestDTO dnaRequestDTO = new DnaRequestDTO();
		dnaRequestDTO.setStrCadenaDna(new ArrayList<String>());
		
		dnaRequestDTO.getStrCadenaDna().add("ATGGGA");
		dnaRequestDTO.getStrCadenaDna().add("GAGGGC");
		dnaRequestDTO.getStrCadenaDna().add("TGACGG");
		dnaRequestDTO.getStrCadenaDna().add("GGGAGG");
		dnaRequestDTO.getStrCadenaDna().add("CCCGTA");
		dnaRequestDTO.getStrCadenaDna().add("GGGGTG");
			
		DnaEntity dnaEntityResponse = new DnaEntity();
		dnaEntityResponse.setNumIdDna(1);
		dnaEntityResponse.setStrEstado("A");
		dnaEntityResponse.setStrEsMutante("S");
		dnaEntityResponse.setDtmFechaCreacion(new Date());
		dnaEntityResponse.setStrCadenaDna(dnaRequestDTO.getStrCadenaDna().toString());		
		
		when(this.dnaRepositoryMock.existsByStrCadenaDna(dnaRequestDTO.getStrCadenaDna().toString())).thenReturn(false);
		when(dnaRepositoryMock.save(any(DnaEntity.class))).thenReturn(dnaEntityResponse);
		
		Assertions.assertDoesNotThrow(() -> crud.saveDna(dnaRequestDTO, true));	
		Assertions.assertThrows(Excepcion403.class, () -> crud.saveDna(dnaRequestDTO, false));		
	}
	
	@Test
	public void findStatsTest() {
		
		LOGGER.info("Entro a metodo findStatsTest()");
		
		DnaResponseStatsDTO dnaResponseStatsDTO = new DnaResponseStatsDTO(new BigDecimal(33), 
																		  new BigDecimal(100), 
																		  "0.33");

		when(this.dnaRepositoryMock.countByStrEsMutanteAndStrEstado("S", "A")).thenReturn(new BigDecimal(33));
		when(this.dnaRepositoryMock.countByStrEsMutanteAndStrEstado("N", "A")).thenReturn(new BigDecimal(100));
		
		assertEquals(dnaResponseStatsDTO, crud.findStats());
	}
	
	
	@Test
	public void findStatsRoundingTest() {
		
		LOGGER.info("Entro a metodo findStatsRoundingTest()");
		
		DnaResponseStatsDTO dnaResponseStatsDTO = new DnaResponseStatsDTO(new BigDecimal(40), 
																		  new BigDecimal(100), 
																		  "0.4");

		when(this.dnaRepositoryMock.countByStrEsMutanteAndStrEstado("S", "A")).thenReturn(new BigDecimal(40));
		when(this.dnaRepositoryMock.countByStrEsMutanteAndStrEstado("N", "A")).thenReturn(new BigDecimal(100));
		
		assertEquals(dnaResponseStatsDTO, crud.findStats());
	}
	
	@Test
	public void findStatsZeroTest() {
		
		LOGGER.info("Entro a metodo findStatsZeroTest()");
		
		DnaResponseStatsDTO dnaResponseStatsDTO = new DnaResponseStatsDTO(new BigDecimal(40), 
																		  new BigDecimal(0), 
																		  "No disponible");

		when(this.dnaRepositoryMock.countByStrEsMutanteAndStrEstado("S", "A")).thenReturn(new BigDecimal(40));
		when(this.dnaRepositoryMock.countByStrEsMutanteAndStrEstado("N", "A")).thenReturn(new BigDecimal(0));
		
		assertEquals(dnaResponseStatsDTO, crud.findStats());
	}
	
	@Test
	public void findStatMethodsTest() {
		
		LOGGER.info("Entro a metodo findStatMethodsTest()");
		
		DnaResponseStatsDTO dnaResponseStatsDTO2 = new DnaResponseStatsDTO(null, null, null);
		DnaResponseStatsDTO dnaResponseStatsDTO = new DnaResponseStatsDTO(new BigDecimal(40), 
																		  new BigDecimal(100), 
																		  "No disponible");
		
		dnaResponseStatsDTO.equals(dnaResponseStatsDTO2);
		dnaResponseStatsDTO.equals(dnaResponseStatsDTO);
	}
}
