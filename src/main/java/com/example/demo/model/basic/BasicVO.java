package com.example.demo.model.basic;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.props.RegisterStatus;

@MappedSuperclass
public class BasicVO implements Serializable, Comparable<BasicVO> {

	private static final long serialVersionUID = 1065155045380561650L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	protected Long id = 0L;
	protected Long version = 0L;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date cadastreDate;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updateDate;	
	protected RegisterStatus status;
	protected Boolean restored = false; 
	
	public BasicVO() {
		super();
	}
	
	public BasicVO(Long id) {
		super();
		this.id = id;
	}
	
	public BasicVO(Long id, Long version) {
		super();
		this.id = id;
		this.version = version;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public Date getCadastreDate() {
		return cadastreDate;
	}

	public void setCadastreDate(Date cadastreDate) {
		this.cadastreDate = cadastreDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public RegisterStatus getStatus() {
		return status;
	}

	public void setStatus(RegisterStatus status) {
		this.status = status;
	}

	

	public Boolean getRestored() {
		return restored;
	}

	public void setRestored(Boolean restored) {
		this.restored = restored;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicVO other = (BasicVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(BasicVO o) {
		return this.getId().compareTo(o.getId());
	}
	
	public boolean isNewRecord() {
		return this.getId() == null || this.getId() <= 0;
	}
	
	public void clearToDuplicate() {
		this.setId(0l);
		this.setVersion(0l);
		this.setStatus(RegisterStatus.ACTIVE);
		this.setCadastreDate(null);
		this.setUpdateDate(null);
	}
	
	public String getTextProperty() {
		return this.toString();
	}
	
}
