package com.francaemp.avaliacao_pratica.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.francaemp.avaliacao_pratica.entities.Address;
import com.francaemp.avaliacao_pratica.entities.AddressId;

public interface AddressRepository extends JpaRepository<Address, AddressId>{

	Page<Address> findAll(Pageable pageable);
}
