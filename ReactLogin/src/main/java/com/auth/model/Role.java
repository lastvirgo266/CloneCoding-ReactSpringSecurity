package com.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60)
	private RoleName name;
	
	
	public Role(RoleName name) {
		this.name = name;
	}
	
	public void setName(RoleName name) {
		this.name = name;
	}
	
	

}
