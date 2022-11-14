package tn.esprit.spring.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Dto.OperationDto;
import tn.esprit.spring.entity.Operation;
import tn.esprit.spring.mappers.CompteBancaireMapper;
import tn.esprit.spring.services.CompteBancaireService;
import tn.esprit.spring.services.OperationService;

@CrossOrigin("*")
@RequestMapping(value = "/api/dossiers")
@RestController()
public class OperationController {

	private final OperationService operationService;
	private final CompteBancaireService compteBancaireService;
	
	public OperationController(OperationService operationService, CompteBancaireService compteBancaireService) {
		this.operationService = operationService;
		this.compteBancaireService = compteBancaireService;
		}
		@GetMapping("{id}")
		public OperationDto findOne(@PathVariable("id") long id) {
		return this.operationService.findOne (id);
		}
		@GetMapping
		public Collection<OperationDto> findAll(){
		return this.operationService.findAll ();
		}
		@PostMapping
		public OperationDto add(@Valid @RequestBody OperationDto operationDto){
		Operation operation = new Operation (CompteBancaireMapper.compteBancaireDtoToCompteBancaire (this.compteBancaireService.findOne (operationDto.getCompteBancaireId ())));
		return this.operationService.save (operationDto);
		}

}
