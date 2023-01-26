package com.example.backend.dto;
import com.example.backend.model.Feestdagen;
import com.example.backend.model.ProductCategorie;

public class ProductDTO {

	private long id;
	private String naam;
	private String beschrijving;
	private ProductCategorie categorie;
	private Feestdagen feestdag;
	private int voorraad;
	private double kosten;
	private double inkoop;
	private String afbeelding;
	

	public String getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}

	public double getInkoop() {
		return inkoop;
	}

	public void setInkoop(double inkoop) {
		this.inkoop = inkoop;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Feestdagen getFeestdag() {
		return feestdag;
	}

	public void setFeestdag(Feestdagen feestdag) {
		this.feestdag = feestdag;
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
}
