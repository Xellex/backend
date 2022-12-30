package com.example.backend.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.example.backend.dto.ResponseDTO;
import com.example.backend.repo.IProductRepository;

@RestController
public class ProductController {

	@Autowired
	private IProductRepository repo;

	@RequestMapping(value = "product/aanmaken", method = RequestMethod.POST)
	public ResponseDTO create(@RequestBody CreateProductDTO product) {

		if (product.getNaam().equals("")) {
			return new ResponseDTO(false, Arrays.asList("Naam is verplicht"));
		}
		
		if (product.getBeschrijving().equals("")) {
			return new ResponseDTO(false, Arrays.asList("Beschrijving is verplicht"));
		}
		
		if (product.getCategorie().equals("")) {
			return new ResponseDTO(false, Arrays.asList("Categorie is verplicht"));
		}
		
		if (product.getKosten()<0) {
			return new ResponseDTO(false, Arrays.asList("Kosten moet groter zijn dan 0"));
		}
		
		ResponseDTO responseDTO = new ResponseDTO();
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

		return new ResponseDTO(true, null);
			
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