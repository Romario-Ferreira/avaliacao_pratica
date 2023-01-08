package com.francaemp.avaliacao_pratica.entities;

import java.io.Serializable;
import java.util.Objects;

public class AddressId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String zipCode;
	private Integer number;
	
	public AddressId () {
		
	}

	public AddressId(String zipCode, Integer number) {
		this.zipCode = zipCode;
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressId other = (AddressId) obj;
		return Objects.equals(number, other.number) && Objects.equals(zipCode, other.zipCode);
	}
	
}
