package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.demo.model.basic.Address;
import com.example.demo.model.basic.BasicVO;

@Entity
public class Branch extends BasicVO {

	private static final long serialVersionUID = 1636207711354644533L;

	private Long idManager;	
	private String name;
	private String responsible;
	private String email;
	private String telephone;
	private String cellphone;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@ManyToOne
	private Company company;

	@OneToMany
	//(cascade = CascadeType.ALL, mappedBy = "branch")	
	private List<User> users;

	public Branch() {
		super();
	}

	public Branch(Long id) {
		super(id);
	}

	public Branch(Long id, String name) {
		super(id);
		this.name = name;
	}

	public Branch(Long id, Long version) {
		super(id, version);
	}

	public Branch(Long id, Long version, String name) {
		super(id, version);
		this.name = name;
	}

	public Long getIdManager() {
		return idManager;
	}

	public void setIdManager(Long idManager) {
		this.idManager = idManager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getInstitution() {
		return company;
	}

	public void setInstitution(Company company) {
		this.company = company;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	/* (non-Javadoc)
	 * @see br.com.droid.frameworkweb.shared.vo.basic.BasicVO#getTextProperty()
	 */
	@Override
	public String getTextProperty() {
		return getName();
	}
	
}
