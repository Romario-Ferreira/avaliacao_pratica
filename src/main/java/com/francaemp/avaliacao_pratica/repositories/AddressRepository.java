package com.francaemp.avaliacao_pratica.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.francaemp.avaliacao_pratica.entities.Address;
import com.francaemp.avaliacao_pratica.entities.AddressId;

public interface AddressRepository extends JpaRepository<Address, AddressId>{

	@Query("SELECT obj FROM Address obj WHERE obj.person.id = :id")
	Page<Address> findAllAddressById(Long id, Pageable pageable);
	
	@Query("SELECT obj FROM Address obj WHERE obj.person.id = :id AND obj.mainAddress = true")
	Address findMainAddressById (Long id);
}
