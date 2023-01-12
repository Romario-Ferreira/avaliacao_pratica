package com.francaemp.avaliacao_pratica.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.francaemp.avaliacao_pratica.entities.Address;
import com.francaemp.avaliacao_pratica.entities.dto.AddressDto;
import com.francaemp.avaliacao_pratica.services.AddressService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping(value = "/{id}/createaddress")
	@Operation(summary = "Create a Address for Person using id")
	public ResponseEntity<Void> createAddressForPerson (@PathVariable Long id, @RequestBody AddressDto addressDto){
		var address = addressService.createAddressForPerson(id, addressService.convertDto(addressDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{zipCode}/{number}").buildAndExpand(address.getZipCode(),address.getNumber()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Find all Person adresses by id")
	public ResponseEntity<Page<AddressDto>> findAllAddressById(@PathVariable Long id, Pageable pageable){
		Page<Address> pageAddress = addressService.findAllAddressById(id, pageable);
		Page<AddressDto> pageAddressDto = pageAddress.map(x -> new AddressDto(x));
		return ResponseEntity.ok().body(pageAddressDto);
	}
	
	@GetMapping(value = "/{id}/mainaddress")
	@Operation(summary = "Find main address by person id")
	public ResponseEntity<AddressDto> findMainAddressById (@PathVariable Long id){
		var mainAddressDto = new AddressDto(addressService.findMainAddressById(id));
		return ResponseEntity.ok().body(mainAddressDto);
		
	}
}
