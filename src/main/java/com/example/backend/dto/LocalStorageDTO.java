package com.example.backend.dto;

import com.example.backend.model.Token;

public class LocalStorageDTO {
	
	private boolean success = false;
	private String naam;
	private Token token;
	private String role;
	
	public LocalStorageDTO(boolean success) {
		this.success = success;
	}

	public LocalStorageDTO(boolean success, String naam, Token token, String role) {
		this.success = success;
		this.naam = naam;
		this.token = token;
		this.role = role;
	}
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
