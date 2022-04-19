package com.api.dna.controladores;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.api.dna.dto.DnaResponseStatsDTO;
import com.api.dna.servicios.crud.DnaServiceCrudLocal;

//@AutoConfigureTestDatabase // realizar test contra una base de datos real.
//@JsonTest // verificar si los serializadores y deserializadores de JSON funcionan de forma correcta
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DnaControladorTest {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(DnaControladorTest.class);
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private DnaServiceCrudLocal dnaServiceCrudLocal;
	
	@Test
	public void findStatsControllerTest() throws Exception {

		LOGGER.info("Entro a metodo findStatsControllerTest()"); 
		
		DnaResponseStatsDTO dnaResponseStatsDTO =  new DnaResponseStatsDTO(new BigDecimal(100), 
																		   new BigDecimal(40), 
																		   "0.4");
		
		when(dnaServiceCrudLocal.findStats()).thenReturn(dnaResponseStatsDTO);
		
		String response = mockMvc.perform(get("http://localhost:7070/stats"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();
 
        LOGGER.info("Response: " + response);
    }


}
