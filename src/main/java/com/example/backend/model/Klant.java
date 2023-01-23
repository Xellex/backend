package com.example.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Klant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String naam;

	@Column(nullable = false)
	private String adres;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = true)
	private String telefoonnummer;

	@Column(nullable = true)
	private String shippingInfo;

	// Relaties
	@OneToOne(mappedBy = "klant")
	private Token token;

	@OneToOne(mappedBy = "klant")
	private Winkelwagen winkelwagen;

	@OneToMany(mappedBy = "klant")
	private List<Bestelling> bestelling;

	@OneToMany(mappedBy = "klant")
	private List<VerlanglijstProduct> verlanglijstproduct;

	public Klant() {
	}

	/**
	 * @param id
	 * @param naam
	 * @param adres
	 * @param email
	 * @param password
	 * @param telefoonnummer
	 * @param shippingInfo
	 * @param token
	 * @param winkelwagen
	 * @param bestelling
	 * @param verlanglijstproduct
	 */
	public Klant(int id, String naam, String adres, String email, String password, String telefoonnummer,
			String shippingInfo, Token token, Winkelwagen winkelwagen, List<Bestelling> bestelling,
			List<VerlanglijstProduct> verlanglijstproduct) {
		super();
		this.id = id;
		this.naam = naam;
		this.adres = adres;
		this.email = email;
		this.password = password;
		this.telefoonnummer = telefoonnummer;
		this.shippingInfo = shippingInfo;
		this.token = token;
		this.winkelwagen = winkelwagen;
		this.bestelling = bestelling;
		this.verlanglijstproduct = verlanglijstproduct;
	}

	// Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(String shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public Winkelwagen getWinkelwagen() {
		return winkelwagen;
	}

	public void setWinkelwagen(Winkelwagen winkelwagen) {
		this.winkelwagen = winkelwagen;
	}

	public List<Bestelling> getBestelling() {
		return bestelling;
	}

	public void setBestelling(List<Bestelling> bestelling) {
		this.bestelling = bestelling;
	}

	public List<VerlanglijstProduct> getVerlanglijstproduct() {
		return verlanglijstproduct;
	}

	public void setVerlanglijstproduct(List<VerlanglijstProduct> verlanglijstproduct) {
		this.verlanglijstproduct = verlanglijstproduct;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Token getToken() {
		return token;
	}
}
