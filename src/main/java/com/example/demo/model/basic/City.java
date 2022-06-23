package com.example.demo.model.basic;

import javax.persistence.Entity;

import com.example.demo.props.State;

@Entity
public class City extends BasicVO {

	private static final long serialVersionUID = -3214833735881296939L;
	
	private String name;
	private State state;
	private Integer ibge;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Integer getIBGE() {
		return ibge;
	}

	public void setIBGE(Integer ibge) {
		this.ibge = ibge;
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
