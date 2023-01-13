package com.francaemp.avaliacao_pratica.controllers;

import java.net.URI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping
	@Operation(summary = "Create a new Person")
	public ResponseEntity<Void> createPerson(@RequestBody PersonDto personDto) {
			var person = personService.convertPersonDto(personDto);
			person = personService.createPerson(person);	
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();		
			return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@Operation(summary = "Update a Person")
	public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
		var person = personService.findById(id);
		BeanUtils.copyProperties(personDto, person);
		person.setId(id);
		personService.updatePerson(person);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Find a Person by id")
	public ResponseEntity<PersonDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(new PersonDto (personService.findById(id)));
	}
	
	@GetMapping
	@Operation(summary = "Find all Persons")
	public ResponseEntity<Page<PersonDto>> findAll(Pageable pageable){
		Page<Person> pagePerson = personService.findAll(pageable);
		Page<PersonDto> pagePersonDto = pagePerson.map(x -> new PersonDto(x));
		return ResponseEntity.ok(pagePersonDto);	
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a Person")
	public ResponseEntity<Void> deleteById (@PathVariable Long id){
		personService.deletePersonById(id);
		return ResponseEntity.noContent().build();
	}
}
