package com.francaemp.avaliacao_pratica.entities.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.francaemp.avaliacao_pratica.entities.Person;

public class PersonDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
	public PersonDto () {		
	}

	public PersonDto(String name, LocalDate birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}

	public PersonDto(Person person) {
		this.name = person.getName();
		this.birthDate = person.getBirthDate();
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
