package com.example.backend;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.Person;

@RestController
public class EndpointProduct {

	@GetMapping("product")
	public List<Product> geefProductenWeer() {
		List<Product> product = new ArrayList();
		Product p = new Product();
		p.setNaam("Feestneus");
		p.setBeschrijving("dit is een mooie feestneus");
		p.setCategorie("neuzen");
		p.setKosten(4.99);
		p.setSubtotal(6.99);
		p.setVoorraad(100);

		product.add(p);
		return product;
	}
}