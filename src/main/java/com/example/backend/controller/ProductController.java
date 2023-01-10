package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.CreateProductDTO;
import com.example.backend.dto.ProductDto;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.model.Product;
import com.example.backend.repo.IProductRepository;

@RestController
public class ProductController {

	@Autowired
	private IProductRepository repo;

	@RequestMapping(value = "product/aanmaken", method = RequestMethod.POST)
	public ResponseDTO create(@RequestBody CreateProductDTO product) {

		ResponseDTO responseDTO = new ResponseDTO();
		ArrayList<String> validaties = new ArrayList<>();

		responseDTO.setSucces(true);

		if (product.getNaam().equals("")) {
			responseDTO.setSucces(false);
			validaties.add("Naam is verplicht");

		}

		if (product.getBeschrijving().equals("")) {
			responseDTO.setSucces(false);
			validaties.add("Beschrijving is verplicht");
		}

		if (product.getCategorie().equals("")) {
			responseDTO.setSucces(false);
			validaties.add("Categorie is verplicht");
		}

		if (product.getKosten() < 0) {
			responseDTO.setSucces(false);
			validaties.add("Kosten moeten groter zijn dan 0");
		}

		responseDTO.setValidaties(validaties);
		if (!responseDTO.isSucces()) {
			return responseDTO;
		}

		Product opslaanProduct = new Product();

		opslaanProduct.setNaam(product.getNaam());
		opslaanProduct.setBeschrijving(product.getBeschrijving());
		opslaanProduct.setVoorraad(0);
		opslaanProduct.setCategorie(product.getCategorie());
		opslaanProduct.setKosten(product.getKosten());
		opslaanProduct.setSubtotal(0);
		opslaanProduct.setAfbeelding(null);
		opslaanProduct.setOntvangen(false);

		repo.save(opslaanProduct);

		return responseDTO;

	}

	@GetMapping("producten")
	public List<ProductDto> geefProductenWeer() {
		// Uiteindelijke lijst
		List<ProductDto> productenDtoLijst = new ArrayList<>();

		// Lijst uit de database
		List<Product> productenDB = repo.findAll();

		for (Product product : productenDB) {
			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setNaam(product.getNaam());
			productDto.setOmschrijving(product.getBeschrijving());
			productDto.setCategorie(product.getCategorie());
			productDto.setSubtotal(product.getSubtotal());

			productenDtoLijst.add(productDto);
		}

		return productenDtoLijst;

	}

	@GetMapping("product/{id}")
	public Product productById(@PathVariable int id) {
		return repo.findById(id).get();
	}


	}

