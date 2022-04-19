package com.api.dna.repositorios;

import com.api.dna.entidades.DnaEntity;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface DnaRepository extends CrudRepository<DnaEntity, Integer> {
	
	@Transactional(readOnly = true)
	Optional<DnaEntity> findByStrCadenaDna(String strCadenaDna);
	
	@Transactional(readOnly = true)
	boolean existsByStrCadenaDna(String strCadenaDna);
	
	@Transactional(readOnly = true)
	BigDecimal countByStrEsMutanteAndStrEstado(String strEsMutante, String strEstado);

}
