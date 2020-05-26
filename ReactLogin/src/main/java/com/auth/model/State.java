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
@Table(name = "states")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class State {
	
	@Id
	@Column(name = "state_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60)
	private StateName name;
	
	public State(StateName name) {
		this.name = name;
	}
	
	public void setName(StateName name) {
		this.name = name;
	}

}
