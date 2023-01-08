package com.francaemp.avaliacao_pratica.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.francaemp.avaliacao_pratica.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	Page<Person> findAll (Pageable pageable);
}
