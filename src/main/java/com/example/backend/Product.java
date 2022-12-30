package com.example.backend;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.web.bind.annotation.GetMapping;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (nullable = false)
	private int Id;
	private String naam;
	private String beschrijving;
	private int voorraad;
	private String categorie;
	private double kosten;
	private double subtotal;
	private String afbeelding;
	private boolean isOntvangen;
	
	// initialisering ............................
	
	public Product() {
		
	}
	
	
	
	public Product(int id, String naam, String beschrijving, int voorraad, String categorie, double kosten,
			double subtotal, String afbeelding, boolean isOntvangen) {
		super();
		Id = id;
		this.naam = naam;
		this.beschrijving = beschrijving;
		this.voorraad = voorraad;
		this.categorie = categorie;
		this.kosten = kosten;
		this.subtotal = subtotal;
		this.afbeelding = afbeelding;
		this.isOntvangen = isOntvangen;

		
	
	}
	
	public boolean isOntvangen() {
		return isOntvangen;
	}
	public void setOntvangen(boolean isOntvangen) {
		this.isOntvangen = isOntvangen;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	public int getVoorraad() {
		return voorraad;
	}
	public void setVoorraad(int voorraad) {
		this.voorraad = voorraad;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
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
	public void setSubtotal(double d) {
		this.subtotal = d;
	}
	public String getAfbeelding() {
		return afbeelding;
	}
	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}
	
	
	// relatie toevoegen 
	// one kant
	
	@OneToMany(mappedBy = "product")
	private List<Recensie> recensies;

	public List<Recensie> getRecensies() {
		return recensies;
	}



	public void setRecensies(List<Recensie> recensies) {
		this.recensies = recensies;
	}
	
	
	

	

}
