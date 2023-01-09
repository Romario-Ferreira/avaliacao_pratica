package com.francaemp.avaliacao_pratica.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.francaemp.avaliacao_pratica.entities.dto.AddressDto;
import com.francaemp.avaliacao_pratica.services.AddressService;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping(value = "/{id}/createaddress")
	public ResponseEntity<Void> createAddressForPerson (@PathVariable Long id, @RequestBody AddressDto addressDto){
		var address = addressService.createAddressForPerson(id, addressService.convertDto(addressDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{zipCode}/{number}").buildAndExpand(address.getZipCode(),address.getNumber()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
