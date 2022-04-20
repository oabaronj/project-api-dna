package com.api.dna.controladores;

import java.util.List;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.api.dna.dto.DnaRequestDTO;
import com.api.dna.excepciones.Exception403;
import com.api.dna.excepciones.Exception422;
import com.api.dna.excepciones.Exception500;
import com.api.dna.servicios.crud.DnaServiceCrudLocal;
import com.api.dna.servicios.validadores.DnaServiceValidadorLocal;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/")
public class DnaControlador {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(DnaControlador.class);
	
	@Autowired 
	private DnaServiceCrudLocal crud;
	@Autowired
	private DnaServiceValidadorLocal validador;
	
	@GetMapping(value="/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findStats(){
		LOGGER.info("Listar Estadisticas");
		return ResponseEntity.ok(this.crud.findStats()); 
	}
	
	@PostMapping(value="/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> procesarDna(@RequestBody HashMap<String, List<String>> json) throws Exception500, Exception422, Exception403,Exception  {
			
		LOGGER.info("Validar DNA");
		
		DnaRequestDTO dnaRequestDTO = this.validador.validarNombreCampoDna(json);
		this.validador.validarEsVacio(dnaRequestDTO);
		this.validador.validarLongitudSecuencia(dnaRequestDTO);
		this.validador.validarCaracteresPermitidos(dnaRequestDTO);
		this.crud.saveDna(dnaRequestDTO, this.validador.isMutant(dnaRequestDTO.getStrCadenaDna()));
		
		return new ResponseEntity<Object>("", HttpStatus.OK); 
	}
	
	@GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll(){
		//return ResponseEntity.ok(Boolean.TRUE);
		LOGGER.info("Consultar todos los DNA");
		return ResponseEntity.ok(this.crud.findAll()); 
	}
	
	@DeleteMapping("/")
	public ResponseEntity<Object> deleteAll() {
		LOGGER.info("Eliminar todos los DNA");
		this.crud.deleteAll();
		return new ResponseEntity<Object>("", HttpStatus.OK);
	}
	
}
