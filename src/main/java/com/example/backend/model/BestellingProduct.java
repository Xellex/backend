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
	private double unitCost;
	private double subtotal;
	private int factuurID;
	private boolean ontvangen;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Bestelling bestelling;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getHoeveelheid() {
		return hoeveelheid;
	}

	public void setHoeveelheid(int hoeveelheid) {
		this.hoeveelheid = hoeveelheid;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int getFactuurID() {
		return factuurID;
	}

	public void setFactuurID(int factuurID) {
		this.factuurID = factuurID;
	}

	public boolean isOntvangen() {
		return ontvangen;
	}

	public void setOntvangen(boolean ontvangen) {
		this.ontvangen = ontvangen;
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
