package com.example.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderID;
	
	private String abc;
}
