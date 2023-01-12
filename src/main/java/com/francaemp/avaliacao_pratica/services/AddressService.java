package com.francaemp.avaliacao_pratica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.francaemp.avaliacao_pratica.entities.Address;
import com.francaemp.avaliacao_pratica.entities.dto.AddressDto;
import com.francaemp.avaliacao_pratica.repositories.AddressRepository;
import com.francaemp.avaliacao_pratica.services.exceptions.AddressException;
import com.francaemp.avaliacao_pratica.services.exceptions.MainAddressException;

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
	
	public Page<Address> findAllAddressById(Long id ,Pageable pageable){
		Page<Address> addresses = addressRepository.findAllAddressById(id,pageable);
		if(addresses.isEmpty()) {
			throw new AddressException("There is no registered address for this id");
		}
		return addresses;
	}
	
	public Address findMainAddressById(Long id) {
		personService.findById(id);
		var mainAddress = addressRepository.findMainAddressById(id);
		if(mainAddress != null) {
			return mainAddress;
		}	
		throw new MainAddressException("There is no registered main address for this id");
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
