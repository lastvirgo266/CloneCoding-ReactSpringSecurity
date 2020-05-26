package com.auth.model.audit;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@MappedSuperclass
@Getter
@JsonIgnoreProperties(
		value = {"createdBy", "updatedBy"},
		allowGetters = true
		)
public abstract class UserDateAudit extends DateAudit {
	
	@CreatedBy
	@Column(updatable = false)
	private Long createdBy;
	
	@LastModifiedBy
	private Long updatedBy;
	
	
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

}
