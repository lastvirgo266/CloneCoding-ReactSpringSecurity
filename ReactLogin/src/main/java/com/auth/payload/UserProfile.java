package com.auth.payload;

import java.time.Instant;

import lombok.Getter;


@Getter
public class UserProfile {
	private Long id;
	private String username;
	private String name;
	private Instant joinedAt;
	
	
	public UserProfile(Long id, String username, String name, Instant joinedAt) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.joinedAt = joinedAt;
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
	
	public void setJoinedAt(Instant joinedAt) {
		this.joinedAt = joinedAt;
	}
	
	
}
