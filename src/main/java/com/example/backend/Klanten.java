package com.example.backend;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Klanten {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int klantID;
	
	private String naam; 
	private String adres;
	
	@Column(nullable = false)
	private LocalDate geboortedatum;
	private String telefoonnummer;
	private String email;
	private String password;
	private String CCinfo;
	private String shippingInfo;
	private float accountBalance;
	private String bestelGeschiedenis;
	public int getKlantID() {
		return klantID;
	}
	public void setKlantID(int klantID) {
		this.klantID = klantID;
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
	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}
	public void setGeboortedatum(LocalDate geboortedatum) {
		this.geboortedatum = geboortedatum;
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
	public String getCCinfo() {
		return CCinfo;
	}
	public void setCCinfo(String cCinfo) {
		CCinfo = cCinfo;
	}
	public String getShippingInfo() {
		return shippingInfo;
	}
	public void setShippingInfo(String shippingInfo) {
		this.shippingInfo = shippingInfo;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getBestelGeschiedenis() {
		return bestelGeschiedenis;
	}
	public void setBestelGeschiedenis(String bestelGeschiedenis) {
		this.bestelGeschiedenis = bestelGeschiedenis;
	}
	
	
	
	

}
