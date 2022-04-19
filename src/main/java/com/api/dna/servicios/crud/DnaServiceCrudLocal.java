package com.api.dna.servicios.crud;

import java.util.List;
import com.api.dna.dto.DnaRequestDTO;
import com.api.dna.dto.DnaResponseDTO;
import com.api.dna.dto.DnaResponseStatsDTO;
import com.api.dna.excepciones.Excepcion403;
import org.springframework.stereotype.Service;

@Service
public interface DnaServiceCrudLocal {
	
	List<DnaResponseDTO> findAll();
	
	DnaResponseStatsDTO findStats();

	void saveDna(DnaRequestDTO dnaRequestDTO, boolean esMutante) throws Excepcion403;
	
	void deleteAll();
	
	//void update(DnaRequestDTO dnaRequestDTO, int numIdDna);
	
	//DnaResponseDTO findByNumIdDna(int numIdDna);
	
	//DnaResponseDTO findByStrCadenaDna(String[] strCadenaDna);
	
	//void deleteById(int numIdDna);

}
