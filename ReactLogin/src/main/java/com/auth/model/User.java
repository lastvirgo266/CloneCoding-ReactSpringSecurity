package com.auth.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;

import com.auth.model.audit.DateAudit;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = {"username"}), @UniqueConstraint(columnNames = {"email"}) })
public class User extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	
	@NotBlank
	@Size(max = 40)
	private String name;
	
	@NotBlank
	@Size(max = 40)
	private String username;
	
	@NaturalId
	@NotBlank
	@Size(max = 60)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 100)
	private String password;
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_states", joinColumns = @JoinColumn(name= "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<State> states = new HashSet<>();
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "state_id"))
	private Set<Role> roles = new HashSet<>();
	
//	@CreatedDate
//	private LocalDateTime createdAt;
//	
//	@CreatedDate
//	private LocalDateTime updatedAt;
	

	public User(String name, String username, String email, String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void setStates(Set<State> states) {
		this.states = states;
	}
	

}
