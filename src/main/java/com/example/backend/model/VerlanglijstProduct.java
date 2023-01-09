package com.example.backend.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class VerlanglijstProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime datumToegevoegd;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Klant klant;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDatumToegevoegd() {
		return datumToegevoegd;
	}

	public void setDatumToegevoegd(LocalDateTime datumToegevoegd) {
		this.datumToegevoegd = datumToegevoegd;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	
	

}
