package com.auth.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Getter
public class UserPrincipal implements UserDetails{
	
	private Long id;
	
	private String name;
	
	private String username;
	
	@JsonIgnore
	private String email;
	
	@JsonIgnore
	private String password;
	
	
	private Collection<? extends GrantedAuthority> authorites;
	
	
	public UserPrincipal(Long id, String name, String username, String email,
			String password, Collection<? extends GrantedAuthority> authorites) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorites = authorites;
	}
	
	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorites = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new UserPrincipal(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), authorites);
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return authorites;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(id, that.id);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	

}
