package com.francaemp.avaliacao_pratica.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.francaemp.avaliacao_pratica.entities.Person;
import com.francaemp.avaliacao_pratica.repositories.PersonRepository;
import com.francaemp.avaliacao_pratica.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class PersonServiceTest {
	
	@InjectMocks
	private PersonService personService;
	
	@Mock
	private PersonRepository personRepository;
	
	private Person personMock;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		initializeMocks();
	}
	
	@Test
	public void shouldCreateAPersonAndReturnThat() {
		when(personRepository.save(personMock)).thenReturn(personMock);
		var person = personService.createPerson(personMock);
		assertEquals(Person.class,person.getClass());		
	}
	
	@Test
	public void shouldThrowObjectNotFoundException_whenFindByIdWithUnexistentId() {
		when(personRepository.findById(anyLong())).thenReturn(Optional.empty());
		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, ()-> personService.findById(anyLong()));
		assertThat(exception.getMessage()).isEqualTo("Objeto não encontrado");	
	}
	
	public void initializeMocks () {
		personMock = new Person("Romario",LocalDate.parse("1994-06-03"));
		personMock.setId(1l);
	}
}
