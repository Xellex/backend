package com.example.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@Column(nullable = true)
	private String afbeelding;

	@Column(nullable = false)
	private String naam;

	@Column(nullable = false)
	private String beschrijving;

	@Column(nullable = false)
	private int voorraad;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Feestdagen feestdag;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductCategorie categorie;

	@Column(columnDefinition = "Decimal(10,2)", nullable = false)
	private double kosten;

	@Column(columnDefinition = "Decimal(10,2)", nullable = false)
	private double inkoop;

	// Relaties
	@ManyToOne
	private Winkelier winkelier;

	@OneToMany(mappedBy = "product")
	@Column(nullable = true)
	private List<VerlanglijstProduct> verlanglijstproducten;
	@OneToMany(mappedBy = "product")
	@Column(nullable = true)
	private List<WinkelwagenProduct> winkelwagenproduct;
	@OneToMany(mappedBy = "product")
	@Column(nullable = true)
	private List<BestellingProduct> bestellingproduct;

	// initialisering... ...............................

	public Product() {

	}

	/**
	 * @param id
	 * @param naam
	 * @param beschrijving
	 * @param voorraad
	 * @param afbeelding
	 * @param categorie
	 * @param feestdag
	 * @param kosten
	 * @param inkoop
	 * @param winkelier
	 * @param verlanglijstproducten
	 * @param winkelwagenproduct
	 * @param bestellingproduct
	 */
	public Product(long id, String naam, String beschrijving, int voorraad, ProductCategorie categorie,
			Feestdagen feestdag, double kosten, double inkoop, Winkelier winkelier,
			List<VerlanglijstProduct> verlanglijstproducten, List<WinkelwagenProduct> winkelwagenproduct,
			List<BestellingProduct> bestellingproduct) {
		super();
		this.id = id;
		this.naam = naam;
		this.beschrijving = beschrijving;
		this.voorraad = voorraad;
		this.categorie = categorie;
		this.feestdag = feestdag;
		this.kosten = kosten;
		this.inkoop = inkoop;
		this.winkelier = winkelier;
		this.verlanglijstproducten = verlanglijstproducten;
		this.winkelwagenproduct = winkelwagenproduct;
		this.bestellingproduct = bestellingproduct;
	}

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

	public int getVoorraad() {
		return voorraad;
	}

	public void setVoorraad(int voorraad) {
		this.voorraad = voorraad;
	}

	public ProductCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(ProductCategorie categorie) {
		this.categorie = categorie;
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

	public Feestdagen getFeestdag() {
		return feestdag;
	}

	public void setFeestdag(Feestdagen feestdag) {
		this.feestdag = feestdag;
	}
}
