package com.test.oauth2.constants;

public enum Users {

	USER("user", "userpass"), 
	ADMIN("admin", "adminpass"),
	OAUTH("client", "password");

	private final String userName;

	private final String password;

	private Users(final String userName, final String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

}
