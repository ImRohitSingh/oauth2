package com.test.oauth2.constants;

public enum Roles {

	USER("USER"), 
	ADMIN("ADMIN");

	private final String role;

	private Roles(final String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}

}
