package com.auth.payload;

import java.time.Instant;

import lombok.Getter;


@Getter
public class UserSummary {
	private Long id;
	private String username;
	private String name;
	
	
	public UserSummary(Long id, String username, String name) {
		this.id = id;
		this.username = username;
		this.name = name;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
	
}
