package com.example.backend.felix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Person;

@RestController
public class FelixEndpoint {

	@GetMapping("bestellingen")
	public List<Bestelling> geefAlleOrders(){
		List<Bestelling> bestellingen = new ArrayList();
		Bestelling b = new Bestelling();
		b.setKlantnaam("Jansen");
		bestellingen.add(b);
		bestellingen.add(new Bestelling());
		return bestellingen;
	}
}
