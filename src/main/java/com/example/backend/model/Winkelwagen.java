package com.example.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Winkelwagen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime datumToegevoegd;

	@OneToMany(mappedBy = "winkelwagen")
	private List<WinkelwagenProduct> winkelwagenproducten;

	@OneToOne
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

	public List<WinkelwagenProduct> getWinkelwagenproducten() {
		return winkelwagenproducten;
	}

	public void setWinkelwagenproducten(List<WinkelwagenProduct> winkelwagenproducten) {
		this.winkelwagenproducten = winkelwagenproducten;
	}

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

}
