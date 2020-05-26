package com.auth.payload;

public class UserIdentityAvailability {
	
	private Boolean availabe;
	
	public UserIdentityAvailability(Boolean available) {
		this.availabe = available;
	}
	
	public Boolean getAvailable() {
		return availabe;
	}
	
	public void setAvailable(Boolean available) {
		this.availabe = available;
	}

}
