package com.example.backend.model;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * @param id
	 * @param klant
	 * @param winkelier
	 * @param randomstring
	 * @param role
	 * @param creationTime
	 */
	public Token(int id, Klant klant, Winkelier winkelier, String randomstring, String role, long creationTime) {
		super();
		this.id = id;
		this.klant = klant;
		this.winkelier = winkelier;
		this.randomstring = randomstring;
		this.role = role;
		this.creationTime = creationTime;
	}
	
	public Token() {
	}

	@OneToOne
	private Klant klant;
	
	@OneToOne
	private Winkelier winkelier;
	
	private String randomstring;
	private String role = "KLANT";
	private long creationTime;
	//private long expirationTime = 86400000; // 1 day in milliseconds

	public Token(int length, String role) {
		this.role = role;
		this.creationTime = System.currentTimeMillis();
		generateNew(length);
	}

	public Token(int length) {
		this.creationTime = System.currentTimeMillis();
		generateNew(length);
	}

	public void generateNew(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random rng = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		this.randomstring = new String(text);
	}

	public boolean isExpired() {
		return System.currentTimeMillis() > creationTime + (60 * 60 * 2 * 1000);
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

	public long getCreationtime() {
		return creationTime;
	}

	public void setCreationtime(Long creationTime) {
		this.creationTime = creationTime;
	}
	
	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public Winkelier getWinkelier() {
		return winkelier;
	}

	public void setWinkelier(Winkelier winkelier) {
		this.winkelier = winkelier;
	}
}
