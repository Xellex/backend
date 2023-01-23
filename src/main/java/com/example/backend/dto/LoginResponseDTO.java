package com.example.backend.dto;

import java.util.List;

public class LoginResponseDTO {

	private String randomstring;
	private String role;
	private String naam;
	private List<String> validaties;
	
	/**
	 * @param randomstring
	 * @param role
	 * @param validaties
	 * @param naam
	 */
	public LoginResponseDTO(String randomstring, String role, List<String> validaties, String naam) {
		super();
		this.randomstring = randomstring;
		this.role = role;
		this.validaties = validaties;
		this.naam = naam;
	}
	public String getRandomstring() {
		return randomstring;
	}
	public void setRandomstring(String randomstring) {
		this.randomstring = randomstring;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getValidaties() {
		return validaties;
	}
	public void setValidaties(List<String> validaties) {
		this.validaties = validaties;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
}
