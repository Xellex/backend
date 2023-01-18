package com.example.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bestelling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private LocalDateTime dateCreated;
	private LocalDateTime dateShipped;
	private String shippingId;
	
	@Enumerated (EnumType.STRING)
	private Bestellingstatus bestellingstatus;
	
	@OneToMany(mappedBy = "bestelling")
	private List<BestellingProduct> bestellingproducten;
	
	@ManyToOne
	private Klant klant;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateShipped() {
		return dateShipped;
	}

	public void setDateShipped(LocalDateTime dateShipped) {
		this.dateShipped = dateShipped;
	}

	public String getShippingId() {
		return shippingId;
	}

	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
	}

	public Bestellingstatus getBestellingstatus() {
		return bestellingstatus;
	}

	public void setBestellingstatus(Bestellingstatus bestellingstatus) {
		this.bestellingstatus = bestellingstatus;
	}

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public List<BestellingProduct> getBestellingproducten() {
		return bestellingproducten;
	}

	public void setBestellingproducten(List<BestellingProduct> bestellingproducten) {
		this.bestellingproducten = bestellingproducten;
	}
	
}
