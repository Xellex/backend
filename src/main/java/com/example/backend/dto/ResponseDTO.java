package com.example.backend.dto;

import java.util.Arrays;
import java.util.List;

public class ResponseDTO {

	private boolean succes = false;

	private List<String> validaties;

	public ResponseDTO() {
		super();
	}

	public ResponseDTO(boolean succes) {
		super();
		
		this.succes = succes;
	}

	public ResponseDTO(boolean succes, List<String> validaties) {
		super();
		this.succes = succes;
		this.validaties = validaties;
	}

	public ResponseDTO(boolean succes, String validatie) {
		super();
		this.succes = succes;
		this.validaties = Arrays.asList(validatie);
	}

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	public List<String> getValidaties() {
		return validaties;
	}

	public void setValidaties(List<String> validaties) {
		this.validaties = validaties;
	}

}
