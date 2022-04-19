package com.api.dna.repositorios;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import com.api.dna.entidades.DnaEntity;
import com.api.dna.dto.DnaResponseStatsDTO;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class DnaRepositoryTest {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(DnaRepositoryTest.class);
	
	@Autowired
	DnaRepository dnaRepository;
	
	@BeforeEach
    public void setUp() {
		
		LOGGER.info("Entro a metodo setUp() - Llenar con registros la tabla antes de cada Test");
		
		List <DnaEntity> listaEntitys = new ArrayList<DnaEntity>();
		listaEntitys.add(new DnaEntity("[ATGCAA]","N"));
		listaEntitys.add(new DnaEntity("[ATGCAD]","N"));
		listaEntitys.add(new DnaEntity("[ATGCAT]","S"));
		listaEntitys.add(new DnaEntity("[TTGCAA]","S"));
		
		dnaRepository.saveAll(listaEntitys); 
    }
	
    @Test
    public void guardarEntidadTest() {
    	
    	LOGGER.info("Entro a metodo guardarEntidadTest()");
    	
    	DnaEntity dnaEntityRequest = new DnaEntity("[ATGCTT]","N"); 
    	    	
      	dnaRepository.save(dnaEntityRequest);     	
        assertThat(dnaEntityRequest.getNumIdDna()).isNotNull();   
    }
    
    @Test
    public void guardarEntidadConCadenaDnaRepetidaTest() {
    	
    	LOGGER.info("Entro a metodo guardarEntidadConCadenaDnaRepetidaTest()");
    	
    	DnaEntity dnaEntityRequest = new DnaEntity("[ATGCAA]", "S");
    	      	       
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> dnaRepository.save(dnaEntityRequest));
    }
    
    @Test
    public void contarEntidadesPorTipoDnaTest() {
    	
    	LOGGER.info("Entro a metodo contarEntidadesPorTipoDnaTest()");
    	
    	DnaResponseStatsDTO dnaResponseStatsDTO = new DnaResponseStatsDTO(null, null, null);
		    	
    	dnaResponseStatsDTO.setStrCountMutantDna(this.dnaRepository.countByStrEsMutanteAndStrEstado("S", "A"));
    	dnaResponseStatsDTO.setStrCountHumanDna(this.dnaRepository.countByStrEsMutanteAndStrEstado("N", "A"));
    	
        assertThat(dnaResponseStatsDTO.getStrCountMutantDna()).isNotNull();
        assertThat(dnaResponseStatsDTO.getStrCountHumanDna()).isNotNull();
    }
}
