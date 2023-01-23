package com.example.backend.dto;

import com.example.backend.model.ProductCategorie;

public class CreateProductDTO {

	private String naam;
	private String beschrijving;
	private ProductCategorie categorie;
	private int voorraad;
	private double kosten;
	private double subtotal;

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

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
}
