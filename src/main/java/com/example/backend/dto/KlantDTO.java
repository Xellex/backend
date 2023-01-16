package com.example.backend.dto;

public class KlantDTO {
	
private String username;
private String password;

/**
 * @param username
 * @param password
 */
public KlantDTO(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}

public KlantDTO(){
	
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
