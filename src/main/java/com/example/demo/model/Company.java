package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.demo.model.basic.Address;
import com.example.demo.model.basic.BasicVO;

@Entity
public class Company extends BasicVO {

	private static final long serialVersionUID = 1636207711354644533L;

	private String fancyName;
	private String companyName;
	private String responsible;
	private String cnpj;
	private String email;
	private String telephone;
	private String cellphone;
	private String website;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Branch> branches;

	public Company() {
		super();
	}

	public Company(Long id) {
		super(id);
	}

	public Company(Long id, Long version) {
		super(id);
		super.version = version;
	}

	public Company(Long id, String name) {
		super(id);
		this.companyName = name;
	}

	public Company(Long id, String companyName, String fancyName, String cnpj) {
		super(id);
		this.companyName = companyName;
		this.fancyName = fancyName;
		this.cnpj = cnpj;
	}
	
	public String getFancyName() {
		return fancyName;
	}

	public void setFancyName(String fancyName) {
		this.fancyName = fancyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getWebsite() {
		return website;
	}

	public void setSite(String website) {
		this.website = website;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String getTextProperty() {
		return getFancyName();
	}
	
}
