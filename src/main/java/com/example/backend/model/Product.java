package com.example.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String naam;

	@Column(nullable = false)
	private String beschrijving;

	@Column(nullable = false)
	private int voorraad;

	@Column(nullable = false)
	private String categorie;

	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private double kosten;

	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private double subtotal;

	@Column(nullable = true)
	private String afbeelding;

	@Column(nullable = true)
	private boolean ontvangen;

	// Relaties
	@ManyToOne
	private Winkelier winkelier;

	@OneToMany(mappedBy = "product")
	private List<VerlanglijstProduct> verlanglijstproducten;
	@OneToMany(mappedBy = "product")
	private List<Recensie> recensies;
	@OneToMany(mappedBy = "product")
	private List<WinkelwagenProduct> winkelwagenproduct;
	@OneToMany(mappedBy = "product")
	private List<BestellingProduct> bestellingproduct;

	// initialisering... ...............................

	public Product() {

	}

	public Product(int id, String naam, String beschrijving, int voorraad, String categorie, double kosten,
			double subtotal, String afbeelding, boolean isOntvangen) {
		super();
		this.id = id;
		this.naam = naam;
		this.beschrijving = beschrijving;
		this.voorraad = voorraad;
		this.categorie = categorie;
		this.kosten = kosten;
		this.subtotal = subtotal;
		this.afbeelding = afbeelding;
		this.ontvangen = isOntvangen;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public String getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}

	public boolean isOntvangen() {
		return ontvangen;
	}

	public void setOntvangen(boolean ontvangen) {
		this.ontvangen = ontvangen;
	}

	public Winkelier getWinkelier() {
		return winkelier;
	}

	public void setWinkelier(Winkelier winkelier) {
		this.winkelier = winkelier;
	}

	public List<VerlanglijstProduct> getVerlanglijstproducten() {
		return verlanglijstproducten;
	}

	public void setVerlanglijstproducten(List<VerlanglijstProduct> verlanglijstproducten) {
		this.verlanglijstproducten = verlanglijstproducten;
	}

	public List<Recensie> getRecensies() {
		return recensies;
	}

	public void setRecensies(List<Recensie> recensies) {
		this.recensies = recensies;
	}

	public List<WinkelwagenProduct> getWinkelwagenproduct() {
		return winkelwagenproduct;
	}

	public void setWinkelwagenproduct(List<WinkelwagenProduct> winkelwagenproduct) {
		this.winkelwagenproduct = winkelwagenproduct;
	}

	public List<BestellingProduct> getBestellingproduct() {
		return bestellingproduct;
	}

	public void setBestellingproduct(List<BestellingProduct> bestellingproduct) {
		this.bestellingproduct = bestellingproduct;
	}

}
