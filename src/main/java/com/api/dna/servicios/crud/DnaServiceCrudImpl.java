package com.api.dna.servicios.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import com.api.dna.dto.DnaRequestDTO;
import com.api.dna.dto.DnaResponseDTO;
import com.api.dna.dto.DnaResponseStatsDTO;
import com.api.dna.entidades.DnaEntity;
import com.api.dna.excepciones.Excepcion403;
import com.api.dna.repositorios.DnaRepository;
import com.api.dna.util.helpers.MHelpers;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class DnaServiceCrudImpl implements DnaServiceCrudLocal {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(DnaServiceCrudImpl.class);
	
	@Autowired
	private DnaRepository dnaRepository;
		
	@Override
	public DnaResponseStatsDTO findStats() {
		DnaResponseStatsDTO stats = new DnaResponseStatsDTO(null, null, null);
		
		stats.setStrCountMutantDna(this.dnaRepository.countByStrEsMutanteAndStrEstado("S", "A"));
		stats.setStrCountHumanDna(this.dnaRepository.countByStrEsMutanteAndStrEstado("N", "A"));
		stats.setStrRatio("No disponible");
		
		if(!stats.getStrCountHumanDna().equals(BigDecimal.ZERO)) {
			String ratio = stats.getStrCountMutantDna().divide(stats.getStrCountHumanDna(), 2, RoundingMode.HALF_EVEN).toString();
			LOGGER.info("ratio 1: [" + ratio + "]");
			if(ratio.substring(ratio.length()-1).equals("0")) {
				ratio = ratio.substring(0,ratio.length()-1);
				LOGGER.info("ratio 2: [" + ratio + "]");
			}
			stats.setStrRatio(ratio);
		}
		LOGGER.info("Objeto stats: [" + stats + "]");
		return stats;
	}

	@Override
	public void saveDna(DnaRequestDTO dnaRequestDTO, boolean esMutante) throws Excepcion403 {
		
		if(!this.dnaRepository.existsByStrCadenaDna(dnaRequestDTO.getStrCadenaDna().toString())) {
			LOGGER.info("DNA no existe en BD");
			
			MHelpers mHelpers = new MHelpers();
			LOGGER.info(mHelpers.toString());
			DnaEntity dnaEntityRequest = MHelpers.modelMapper().map(dnaRequestDTO, DnaEntity.class);
			
			if(esMutante) {
				DnaEntity dnaEntityResponse = this.dnaRepository.save(dnaEntityRequest);
				LOGGER.info("DNA Mutante: ["+ dnaEntityResponse + "]");				
			}else{				
				dnaEntityRequest.setStrEsMutante("N");
				DnaEntity dnaEntityResponse = dnaRepository.save(dnaEntityRequest);
				LOGGER.warn("DNA No Mutante: [" + dnaEntityResponse + "]");
				throw new Excepcion403("Forbidden - DNA No Mutante");				
			}			
		}else if(!esMutante) {			
			LOGGER.warn("DNA existe en BD - DNA No Mutante");
			throw new Excepcion403("Forbidden - DNA No Mutante");			
		}else {
			LOGGER.info("DNA existe en BD - DNA Mutante");
		}	
	}

	@Override
	public List<DnaResponseDTO> findAll() {
			
		List<DnaResponseDTO> listaDnaResponseDTO = new ArrayList<>();
		
		Iterable<DnaEntity> listaDnaEntity = this.dnaRepository.findAll();
		
		for(DnaEntity dnaEntity:listaDnaEntity) {
			DnaResponseDTO dnaResponseDTO = MHelpers.modelMapper().map(dnaEntity, DnaResponseDTO.class);
			if(dnaResponseDTO.getStrEstado().equals("A")) {
				listaDnaResponseDTO.add(dnaResponseDTO);
			}			
		}
		return listaDnaResponseDTO;
	}

	@Override
	public void deleteAll() {
		this.dnaRepository.deleteAll();
	}
}
