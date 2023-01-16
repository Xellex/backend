package com.example.backend.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.CreateWinkelwagenProductDTO;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.model.Klant;
import com.example.backend.model.Product;
import com.example.backend.model.Winkelwagen;
import com.example.backend.model.WinkelwagenProduct;
import com.example.backend.repo.IKlantenRepository;
import com.example.backend.repo.IProductRepository;
import com.example.backend.repo.IWinkelwagenProductRepository;
import com.example.backend.repo.IWinkelwagenRepository;
import com.example.backend.service.AuthenticationService;

@RestController
public class WinkelwagenController {

	@Autowired
	private IWinkelwagenRepository winkelwagenrepo;

	@Autowired
	private IKlantenRepository klantrepo;

	@Autowired
	private IProductRepository productrepo;

	@Autowired
	private IWinkelwagenProductRepository winkelwagenProductRepo;

	@Autowired
	private AuthenticationService authService;

	@PostMapping("winkelwagen/product")
	public ResponseDTO aanmaken(@RequestBody CreateWinkelwagenProductDTO dto,
			@RequestHeader("Authentication") String authenticationToken) {
		boolean rights = authService.doesTokenHaveRole(authenticationToken, "KLANT");
		// vinden van winkelwagen bij de klant
		Klant klant = klantrepo.findById(1).orElse(null);
		Optional<Winkelwagen> wwdb = winkelwagenrepo.findByKlant(klant);
		if (wwdb.isEmpty()) {

			Winkelwagen winkelwagen = new Winkelwagen();
			winkelwagen.setKlant(klant);
			winkelwagen.setDatumToegevoegd(LocalDateTime.now());
			winkelwagenrepo.save(winkelwagen);
			wwdb = Optional.of(winkelwagen);
		}
		// vinden van winkelwagenproduct bij winkelwagen
		Optional<Product> optionalProduct = productrepo.findById(dto.getProductId());

		// als dat nul is, wordt dat 1
		if (optionalProduct.isEmpty()) {
			return new ResponseDTO(false, "product bestaat niet");
		}
		// en als dat 1 is wordt dat +1
		Optional<WinkelwagenProduct> optionalWinkelwagenProduct = winkelwagenProductRepo
				.findByWinkelwagenAndProduct(wwdb.get(), optionalProduct.get());
		if (optionalWinkelwagenProduct.isEmpty()) {
			WinkelwagenProduct winkelwagenProduct = new WinkelwagenProduct();
			winkelwagenProduct.setProduct(optionalProduct.get());
			winkelwagenProduct.setHoeveelheid(1);
			winkelwagenProduct.setAangemaakt(LocalDate.now());
			winkelwagenProduct.setWinkelwagen(klant.getWinkelwagen());

			winkelwagenProductRepo.save(winkelwagenProduct);
			return new ResponseDTO(true);
		} else {
			WinkelwagenProduct winkelwagenProduct = optionalWinkelwagenProduct.get();
			winkelwagenProduct.setHoeveelheid(winkelwagenProduct.getHoeveelheid() + 1);
			winkelwagenProductRepo.save(winkelwagenProduct);
			return new ResponseDTO(true);
		}
	}

	// check of winkelwagen aanwezig is voor de huidige user
	// als die nog niet bestaat, dan aanmaken
	// dan product toevoegen
//		Klant klant = klantrepo.findById(1).get();
//		if (klant.getWinkelwagen() == null) {
//
//			Winkelwagen winkelwagen = new Winkelwagen();
//			winkelwagen.setKlant(klant);
//			winkelwagen.setDatumToegevoegd(LocalDateTime.now());
//			repo.save(winkelwagen);
//			klant.setWinkelwagen(winkelwagen);
//		}
//
//		Product product = productrepo.findById(dto.getProductId()).get();
//		WinkelwagenProduct winkelwagenProduct = new WinkelwagenProduct();
//		winkelwagenProduct.setProduct(product);
//		winkelwagenProduct.setHoeveelheid(dto.getHoeveelheid());
//		winkelwagenProduct.setAangemaakt(LocalDate.now());
//		winkelwagenProduct.setWinkelwagen(klant.getWinkelwagen());
//
//		winkelwagenProductRepo.save(winkelwagenProduct);

//	@RequestMapping(value = "winkelwagen/aanmaken", method = RequestMethod.POST)
//	public void create(@RequestBody WinkelwagenProduct winkelwagen) {
//		repo.save(winkelwagen);
//
//	}
//
//	@RequestMapping(value = "winkelwagen/{id}", method = RequestMethod.PUT)
//	public void update(@PathVariable long id, @RequestBody WinkelwagenProduct winkelwagen) {
//		// database winkelwagen
//		WinkelwagenProduct ww = repo.findById(id).get();
//
//		// update de db winkelwagen met winkelwagen uit de request body
//		ww.setHoeveelheid(winkelwagen.getHoeveelheid());
//		ww.setAangemaakt(winkelwagen.getAangemaakt());
//		// save the database winkelwagen
//		repo.save(ww);
//	}
//
//	@RequestMapping(value = "winkelwagen/verwijderen/{id}", method = RequestMethod.DELETE)
//	public void delete(@PathVariable long id) {
//		repo.deleteById(id);
//	}
//
//	@RequestMapping(value = "winkelwagens/opvragen/{id}")
//	public Winkelwagen winkelwagenid(@PathVariable long id) {
//		return repo.findById(id).get();
//	}
}
