package com.example.demo.model.basic;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Neighborhood extends BasicVO {

	private static final long serialVersionUID = -2877260329538478300L;
	
	private String name;
	@ManyToOne
	private City city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public String getTextProperty() {
		return toString();
	}
	
}
