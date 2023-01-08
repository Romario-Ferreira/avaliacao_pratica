package com.francaemp.avaliacao_pratica.entities;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String address;
	private String zipCode;
	private Integer number;
	private String city;
	private boolean mainAddress;
	
	private Person person;
	
	public Address() {
	}

	public Address(String address, String zipCode, Integer number, String city, boolean mainAddress) {
		this.address = address;
		this.zipCode = zipCode;
		this.number = number;
		this.city = city;
		this.mainAddress = mainAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(boolean mainAddress) {
		this.mainAddress = mainAddress;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
		Address other = (Address) obj;
		return Objects.equals(number, other.number) && Objects.equals(zipCode, other.zipCode);
	}
	


}
