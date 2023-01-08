package com.francaemp.avaliacao_pratica.entities.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PersonDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private LocalDate birthDate;
	
	public PersonDto () {		
	}

	public PersonDto(String name, LocalDate birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	

}
