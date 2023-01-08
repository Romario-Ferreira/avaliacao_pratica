package com.francaemp.avaliacao_pratica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.francaemp.avaliacao_pratica.entities.Person;
import com.francaemp.avaliacao_pratica.entities.dto.PersonDto;
import com.francaemp.avaliacao_pratica.repositories.AddressRepository;
import com.francaemp.avaliacao_pratica.repositories.PersonRepository;
import com.francaemp.avaliacao_pratica.services.exceptions.ObjectNotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Person createPerson (Person person) {
		return personRepository.save(person);
	}
	
	public Person findById (Long id) {
		var person = personRepository.findById(id);
		return person.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Page<Person> findAll(Pageable pageable){
		return personRepository.findAll(pageable);
	}
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	public void deletePersonById (Long id) {
		findById(id);
		personRepository.deleteById(id);
	}
	
	public Person updatePerson (Person person) {
		return personRepository.saveAndFlush(person);
	}
	
	public Person convertPersonDto(PersonDto personDto) {	
		return new Person(personDto.getName(), personDto.getBirthDate());
	}
}
