package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.example.demo.model.basic.BasicVO;

@Entity
public class Permission extends BasicVO {

	private static final long serialVersionUID = 7982690128218204345L;

	@ManyToOne
	private User user;
	@ManyToOne
	private UserLevel userLevel;	
	private String idComponent;
	private Boolean open = false;
	private Boolean view = false;
	private Boolean include = false;
	private Boolean edit = false;
	private Boolean delete = false;
	private Boolean search = false;
	private Boolean restore = false;
	private Boolean duplicate = false;
	
	public Permission() {
		super();
	}

	public Permission(Long id) {
		super(id);
	}
	
	public Permission(String idComponent) {
		super();
		
		this.idComponent = idComponent;
	}
	
	public Permission(String idComponent, Boolean open, Boolean view, 
			Boolean include, Boolean edit, Boolean delete, 
			Boolean search, Boolean restore, Boolean duplicate) {
		super();
		
		this.idComponent = idComponent;
		this.open = open;
		this.include = include;
		this.edit = edit;
		this.delete = delete;
		this.view = view;
		this.search = search;
		this.restore = restore;
		this.duplicate = duplicate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserLevel getLevel() {
		return userLevel;
	}

	public void setLevel(UserLevel level) {
		this.userLevel = level;
	}

	public String getIdComponent() {
		return idComponent;
	}

	public void setIdComponent(String idComponent) {
		this.idComponent = idComponent;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	public Boolean getView() {
		return view;
	}
	
	public void setView(Boolean view) {
		this.view = view;
	}

	public Boolean getInclude() {
		return include;
	}

	public void setInclude(Boolean include) {
		this.include = include;
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Boolean getSearch() {
		return search;
	}

	public void setSearch(Boolean search) {
		this.search = search;
	}

	public Boolean getRestore() {
		return restore;
	}

	public void setRestore(Boolean restore) {
		this.restore = restore;
	}

	public Boolean getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(Boolean duplicate) {
		this.duplicate = duplicate;
	}
	
	public void updatePermissions(Permission permission) {
		this.setDelete(permission.getDelete());
		this.setDuplicate(permission.getDuplicate());
		this.setEdit(permission.getEdit());
		this.setInclude(permission.getInclude());
		this.setOpen(permission.getOpen());
		this.setRestore(permission.getRestore());
		this.setSearch(permission.getSearch());
		this.setView(permission.getView());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idComponent == null) ? 0 : idComponent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (idComponent == null) {
			if (other.idComponent != null)
				return false;
		} else if (!idComponent.equals(other.idComponent))
			return false;
		return true;
	}
	
}
