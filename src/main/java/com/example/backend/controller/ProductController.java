package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Product;
import com.example.backend.dto.CreateProductDTO;
import com.example.backend.dto.ProductDto;
import com.example.backend.repo.IProductRepository;

@RestController
public class ProductController {

	@Autowired
	private IProductRepository repo;

	@RequestMapping(value = "product/aanmaken", method = RequestMethod.POST)
	public void create(@RequestBody CreateProductDTO product) {

		Product productopslaan = new Product(0, // id
				product.getNaam(), // naam
				product.getBeschrijving(), // beschrijving
				0, // voorraad
				product.getCategorie(), // categorie
				0, // kosten
				0, // subtotaal
				null, // is ontvangen
				false);

		repo.save(productopslaan);

	}

	@GetMapping
	public List<ProductDto> geefProductenWeer() {
		// Uiteindelijke lijst
		List<ProductDto> productenDtoLijst = new ArrayList();

		// Lijst uit de database
		List<Product> productenDB = new ArrayList<>();

		for (Product product : productenDB) {
			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setNaam(product.getNaam());
			productDto.setOmschrijving(product.getBeschrijving());

			productenDtoLijst.add(productDto);
		}

		return productenDtoLijst;
	}
}