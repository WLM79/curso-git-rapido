package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.model.basic.BasicVO;

@Entity
@Table(name="application_user")
public class User extends BasicVO {

	private static final long serialVersionUID = -8375727165295016775L;
	
	private String name;
	private String login;
	private String password;
	private String cellphone;
	private String email;
	@ManyToOne
	private UserLevel level;
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	@ManyToOne
	private Branch branch;
	
	@OneToMany
	private List<Permission> permission;
	
	public User() {
		super();
	}
	
	public User(Long id) {
		super(id);
	}

	public User(Long id, String name) {
		this(id);
		this.name = name;
	}
	
	public User(Long id, Long version) {
		super(id, version);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserLevel getLevel() {
		return level;
	}

	public void setLevel(UserLevel level) {
		this.level = level;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
	
	@Override
	public void clearToDuplicate() {
		super.clearToDuplicate();
		
		this.setLogin("");
		this.setPassword("");
	}
	
	@Override
	public String getTextProperty() {
		return getLogin();	
	}
	
}
