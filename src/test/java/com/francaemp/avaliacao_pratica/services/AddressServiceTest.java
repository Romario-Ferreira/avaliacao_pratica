package com.francaemp.avaliacao_pratica.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.francaemp.avaliacao_pratica.entities.Address;
import com.francaemp.avaliacao_pratica.entities.Person;
import com.francaemp.avaliacao_pratica.repositories.AddressRepository;
import com.francaemp.avaliacao_pratica.services.exceptions.MainAddressException;

@SpringBootTest
public class AddressServiceTest {

	@InjectMocks
	private AddressService addressService;
	
	@Mock
	private AddressRepository addressRepository;
	
	@Mock
	private PersonService personService;
	
	private Person personMock;
	
	private Address addressMock;	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		initializeMocks();
	}
	
	@Test
	public void shouldSetMainAddressToFalseForAllOthersAddresses_whenNewAddressCreatedHaveMainAddressAttributeTrue() {
		when(personService.findById(anyLong())).thenReturn(personMock);	
		var address = new Address("Avenida Alguma Coisa", "36460000", 320, "Alguma Cidade", true);
		addressService.createAddressForPerson(personMock.getId(), address);
		assertEquals(addressMock.isMainAddress(),false);
	}
	
	@Test
	public void shouldThrowMainAddressException_whenHaveNoMainAddressRegistered() {
		when(addressRepository.findMainAddressById(anyLong())).thenReturn(null);
		MainAddressException exception = assertThrows(MainAddressException.class, () -> addressService.findMainAddressById(personMock.getId()));
		assertThat(exception.getMessage()).isEqualTo("There is no registered main address for this id");
	}
	
	public void initializeMocks () {
		personMock = new Person("Romario",LocalDate.parse("1994-06-03"));
		personMock.setId(1l);
		addressMock = new Address(
				"Rua Qualquer",
				"38400000",
				250,
				"Uberlandia",
				true
				);
		addressMock.setPerson(personMock);
		personMock.getAddresses().add(addressMock);
	}
}
