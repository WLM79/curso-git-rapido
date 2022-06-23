package com.example.demo.model.basic;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Address extends BasicVO {

	private static final long serialVersionUID = -802914976913804228L;
	
	private String zipCode;
	private String address;
	private Long number;
	private String complement;
	private Double latitude;
	private Double longitude;
	
	@ManyToOne
	private Neighborhood neighborhood;	
	
	public Address() {
		super();
	}
	
	public Address(Long id) {
		super(id);
	}
	
	public Address(Long id, Long version) {
		super(id, version);
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setCep(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}

}
