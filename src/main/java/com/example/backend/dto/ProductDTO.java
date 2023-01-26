package com.example.backend.dto;

import com.example.backend.model.Feestdagen;
import com.example.backend.model.ProductCategorie;

public class ProductDTO {

	private long id;
	private String afbeelding;
	private String naam;
	private String beschrijving;
	private Feestdagen feestdag;
	private ProductCategorie categorie;
	private int voorraad;
	private double kosten;
	private double inkoop;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public Feestdagen getFeestdag() {
		return feestdag;
	}

	public void setFeestdag(Feestdagen feestdag) {
		this.feestdag = feestdag;
	}

	public ProductCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(ProductCategorie categorie) {
		this.categorie = categorie;
	}

	public int getVoorraad() {
		return voorraad;
	}

	public void setVoorraad(int voorraad) {
		this.voorraad = voorraad;
	}

	public double getKosten() {
		return kosten;
	}

	public void setKosten(double kosten) {
		this.kosten = kosten;
	}

	public double getInkoop() {
		return inkoop;
	}

	public void setInkoop(double inkoop) {
		this.inkoop = inkoop;
	}
}
