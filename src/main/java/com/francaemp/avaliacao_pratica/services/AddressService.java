package com.francaemp.avaliacao_pratica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francaemp.avaliacao_pratica.entities.Address;
import com.francaemp.avaliacao_pratica.entities.dto.AddressDto;
import com.francaemp.avaliacao_pratica.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonService personService;
	
	public Address createAddressForPerson (Long id, Address address) {
		var person = personService.findById(id);
		if(address.isMainAddress()) {
			person.getAddresses().stream().filter(x -> x.isMainAddress()).forEach(x -> x.setMainAddress(false));
		}
		person.getAddresses().add(address);
		address.setPerson(person);
		return addressRepository.save(address);
		
		
	}

	public Address convertDto(AddressDto addressDto) {
		var address = new Address(
				addressDto.getAddress(),
				addressDto.getZipCode(),
				addressDto.getNumber(),
				addressDto.getCity(),
				addressDto.isMainAddress()
			);
		return address;
	}
}
