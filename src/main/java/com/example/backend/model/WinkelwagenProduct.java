package com.example.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WinkelwagenProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int hoeveelheid;

	@ManyToOne
	private Winkelwagen winkelwagen;
	@ManyToOne
	private Product product;

	@Column(nullable = false)
	private LocalDate aangemaakt;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHoeveelheid() {
		return hoeveelheid;
	}

	public void setHoeveelheid(int hoeveelheid) {
		this.hoeveelheid = hoeveelheid;
	}

	public LocalDate getAangemaakt() {
		return aangemaakt;
	}

	public void setAangemaakt(LocalDate aangemaakt) {
		this.aangemaakt = aangemaakt;
	}

	public Winkelwagen getWinkelwagen() {
		return winkelwagen;
	}

	public void setWinkelwagen(Winkelwagen winkelwagen) {
		this.winkelwagen = winkelwagen;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
