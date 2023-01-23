package com.example.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BestellingProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int hoeveelheid;
	private double kosten;
	private double inkoop;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Bestelling bestelling;
	
	public BestellingProduct() {
	}

	/**
	 * @param id
	 * @param hoeveelheid
	 * @param kosten
	 * @param inkoop
	 * @param product
	 * @param bestelling
	 */
	public BestellingProduct(long id, int hoeveelheid, double kosten, double inkoop, Product product,
			Bestelling bestelling) {
		super();
		this.id = id;
		this.hoeveelheid = hoeveelheid;
		this.kosten = kosten;
		this.inkoop = inkoop;
		this.product = product;
		this.bestelling = bestelling;
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



	public void setId(long id) {
		this.id = id;
	}



	public long getId() {
		return id;
	}

	public int getHoeveelheid() {
		return hoeveelheid;
	}

	public void setHoeveelheid(int hoeveelheid) {
		this.hoeveelheid = hoeveelheid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Bestelling getBestelling() {
		return bestelling;
	}

	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	}
}
