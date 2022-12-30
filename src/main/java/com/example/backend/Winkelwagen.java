package com.example.backend;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Winkelwagen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int hoeveelheid;
	
	@OneToOne (mappedBy = "winkelwagen")
	private Klanten klant;
	
	@Column(nullable = false)
	private LocalDate aangemaakt;

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

	public LocalDate getAangemaakt() {
		return aangemaakt;
	}

	public void setAangemaakt(LocalDate aangemaakt) {
		this.aangemaakt = aangemaakt;
	}
	
	
}
