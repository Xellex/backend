package com.example.backend;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Verlanglijst {

	@Id
	private long id;
	@OneToOne(mappedBy = "verlanglijstje")
	@JoinColumn
	private Klanten klant;

}
