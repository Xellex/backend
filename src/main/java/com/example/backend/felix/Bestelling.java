package com.example.backend.felix;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bestelling {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	String klantnaam;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKlantnaam() {
		return klantnaam;
	}

	public void setKlantnaam(String klantnaam) {
		this.klantnaam = klantnaam;
	}
	
	
}
