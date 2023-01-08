package com.francaemp.avaliacao_pratica.controllers;

import java.net.URI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.francaemp.avaliacao_pratica.entities.Person;
import com.francaemp.avaliacao_pratica.entities.dto.PersonDto;
import com.francaemp.avaliacao_pratica.services.PersonService;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<Void> createPerson(@RequestBody PersonDto personDto) {
		var person = personService.convertPersonDto(personDto);
		person = personService.createPerson(person);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}/update")
	public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
		var person = personService.findById(id);
		BeanUtils.copyProperties(personDto, person);
		person.setId(id);
		personService.updatePerson(person);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PersonDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(new PersonDto (personService.findById(id)));
	}
	
	@GetMapping
	public ResponseEntity<Page<Person>> findAll(Pageable pageable){
		return ResponseEntity.ok(personService.findAll(pageable));
		
	}
}
