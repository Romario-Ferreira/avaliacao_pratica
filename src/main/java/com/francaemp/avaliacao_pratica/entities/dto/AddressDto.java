package com.francaemp.avaliacao_pratica.entities.dto;

import java.io.Serializable;

public class AddressDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String address;
	private String zipCode;
	private Integer number;
	private String city;
	private boolean mainAddress;
	
	public AddressDto () {
	}

	public AddressDto(String address, String zipCode, Integer number, String city, boolean mainAddress) {
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


}
