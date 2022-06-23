package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.example.demo.model.basic.BasicVO;

@Entity
public class UserLevel extends BasicVO {

	private static final long serialVersionUID = 372976092470172527L;

	private String name;
		
	@OneToMany
	//(cascade = CascadeType.ALL, mappedBy = "userLevel")	
	private List<Permission> level;
	
	public UserLevel() {
		super();
	}
	
	public UserLevel(String name) {
		super();
		this.name = name;
	}

	public UserLevel(long id) {
		super(id);
	}
	
	public UserLevel(long id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissionList() {
		return level;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.level = permissionList;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
}
