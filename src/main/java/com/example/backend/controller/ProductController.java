package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.dto.ProductDTO;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.model.Feestdagen;
import com.example.backend.model.Product;
import com.example.backend.model.ProductCategorie;
import com.example.backend.repo.IProductRepository;

@RestController
public class ProductController {

	@Autowired
	private IProductRepository repo;

	@GetMapping("product/categorieen")
	public List<String> getMyEnumValuesCategorieen() {
		List<String> categoryNames = new ArrayList<>();
		for (ProductCategorie category : ProductCategorie.values()) {
			categoryNames.add(category.name());
		}
		return categoryNames;
	}

	@GetMapping("product/feestdagen")
	public List<String> getMyEnumValuesFeestdagen() {
		List<String> feestdagNames = new ArrayList<>();
		for (Feestdagen category : Feestdagen.values()) {
			feestdagNames.add(category.name());
		}
		return feestdagNames;
	}

	@PostMapping(value = "product/toevoegen")
	public ResponseDTO maakProductAann(@RequestBody ProductDTO product, MultipartFile image) {

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
		opslaanProduct.setVoorraad(product.getVoorraad());
		opslaanProduct.setCategorie(product.getCategorie());
		opslaanProduct.setKosten(product.getKosten());
		opslaanProduct.setInkoop(product.getVoorraad());
		opslaanProduct.setFeestdag(product.getFeestdag());
		//opslaanProduct.setImage(product.getImage());
		repo.save(opslaanProduct);

		return responseDTO;
	}

	@GetMapping("producten")
	public List<ProductDTO> geefProductenWeer() {

		List<ProductDTO> productenDTOLijst = new ArrayList<>();
		List<Product> productenDB = repo.findAll();

		for (Product product : productenDB) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setNaam(product.getNaam());
			productDTO.setBeschrijving(product.getBeschrijving());
			productDTO.setVoorraad(product.getVoorraad());
			productDTO.setCategorie(product.getCategorie());
			productDTO.setKosten(product.getKosten());
			productDTO.setFeestdag(product.getFeestdag());
			productenDTOLijst.add(productDTO);
		}
		return productenDTOLijst;
	}

	@GetMapping("product/{id}")
	public ProductDTO productById(@PathVariable long id) {
		Product product = repo.findById(id).get();
		ProductDTO productDto = new ProductDTO();
		productDto.setId(product.getId());
		productDto.setNaam(product.getNaam());
		productDto.setBeschrijving(product.getBeschrijving());
		productDto.setVoorraad(product.getVoorraad());
		productDto.setCategorie(product.getCategorie());
		productDto.setKosten(product.getKosten());
		return productDto;
	}

}
