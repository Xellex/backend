package com.example.backend.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.CreateWinkelwagenProductDTO;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.model.Bestelling;
import com.example.backend.model.BestellingProduct;
import com.example.backend.model.Bestellingstatus;
import com.example.backend.model.Klant;
import com.example.backend.model.Product;
import com.example.backend.model.Token;
import com.example.backend.model.Winkelwagen;
import com.example.backend.model.WinkelwagenProduct;
import com.example.backend.repo.IBestellingProductRepository;
import com.example.backend.repo.IBestellingRepository;
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
	private IBestellingRepository bestellingrepo;

	@Autowired
	private IBestellingProductRepository bestellingproductrepo;

	@Autowired
	private AuthenticationService authService;

	@PostMapping("winkelwagen/product")
	public ResponseDTO aanmaken(@RequestBody CreateWinkelwagenProductDTO dto,
			@RequestHeader("Authentication") String authenticationToken) {
		Optional<Token> optionalToken = authService.findByToken(authenticationToken);
		if (optionalToken.isEmpty())
			return new ResponseDTO(false, "No token");

		boolean rights = authService.doesTokenHaveRole(authenticationToken, "KLANT");
		if (!rights)
			return new ResponseDTO(false, "Geen rechten");

		Klant klant = optionalToken.get().getKlant();

		// vinden van winkelwagen bij de klant
		Winkelwagen ww = klant.getWinkelwagen();
		// vinden van winkelwagenproduct bij winkelwagen
		Product productdb = productrepo.findById(dto.getProductId()).orElse(null);

		// als dat nul is, wordt dat 1
		
		// en als dat 1 is wordt dat +1
		if (productdb != null) {
			WinkelwagenProduct wwpdb = winkelwagenProductRepo.findByWinkelwagenAndProduct(ww, productdb).orElse(null);
			if (wwpdb == null) {
				WinkelwagenProduct winkelwagenProduct = new WinkelwagenProduct();
				winkelwagenProduct.setProduct(productdb);
				winkelwagenProduct.setHoeveelheid(1);
				winkelwagenProduct.setAangemaakt(LocalDate.now());
				if (ww == null) {

					Winkelwagen winkelwagen = new Winkelwagen();
					winkelwagen.setKlant(klant);
					winkelwagen.setDatumToegevoegd(LocalDateTime.now());
					winkelwagenrepo.save(winkelwagen);
					winkelwagenProduct.setWinkelwagen(winkelwagen);
				} else {
					winkelwagenProduct.setWinkelwagen(klant.getWinkelwagen());

				}

				winkelwagenProductRepo.save(winkelwagenProduct);
				return new ResponseDTO(true);
			} else {
				wwpdb.setHoeveelheid(wwpdb.getHoeveelheid() + 1);
				winkelwagenProductRepo.save(wwpdb);
				return new ResponseDTO(true);
			}
		};
		return new ResponseDTO(false, "product bestaat niet");
		
		
	}

	@PostMapping("winkelwagen/bestellen")
	public ResponseDTO bestellen(@RequestHeader("Authentication") String authenticationToken) {
		Optional<Token> optionalToken = authService.findByToken(authenticationToken);
		if (optionalToken.isEmpty())
			return new ResponseDTO(false, "No token");

		boolean rights = authService.doesTokenHaveRole(authenticationToken, "KLANT");
		// if (!rights)return new ResponseDTO(false, "Geen rechten");

		// klant uit token
		Klant klant = optionalToken.get().getKlant();
		// winkelwagen uit klant
		Winkelwagen wwklant = klant.getWinkelwagen();
		// winkelwagenproducten geassocieerd met ww uit de db
		List<WinkelwagenProduct> winkelwagenproductList = winkelwagenProductRepo.findByWinkelwagen(wwklant);

		// Voor elk product halen we de meest recente prijs op en stoppen de product in
		// de bestelling
		if (!winkelwagenproductList.isEmpty()) {

			Bestelling bestelling = new Bestelling();

			// creeer bestellingproduct
			BestellingProduct bestellingProduct = new BestellingProduct();
			List<BestellingProduct> bestellingproducten = new ArrayList<BestellingProduct>();

			// producten uit de winkelwagenproducten ophalen om zo de meest recente prijs te
			// krijgen
			for (WinkelwagenProduct winkelwagenProduct : winkelwagenproductList) {
				Product product = winkelwagenProduct.getProduct();

				// sla de hoeveelheid van het product in bestelling product
				bestellingProduct.setHoeveelheid(winkelwagenProduct.getHoeveelheid());

				// sla de productprijs op in bestelling product
				bestellingProduct.setUnitCost(product.getKosten());

				// Vermedigvuldig voor het totaal
				bestellingProduct.setSubtotal(product.getKosten() * winkelwagenProduct.getHoeveelheid());

				// Voed het gecreeerde bestellingsproduct toe aan een lijst.
				bestellingproducten.add(bestellingProduct);

				bestellingProduct.setBestelling(bestelling);
				bestellingproductrepo.save(bestellingProduct);
			}
			// voeg de net gecreeerde lijst met bestellingproducten toe aan bestelling.
			bestelling.setBestellingproducten(bestellingproducten);

			bestelling.setDateCreated(LocalDateTime.now());
			bestelling.setBestellingstatus(Bestellingstatus.CREATED);
			bestelling.setKlant(klant);
			bestellingrepo.save(bestelling);
			return new ResponseDTO(true);
		}
		return new ResponseDTO(false, "geen producten in winkelmand");
	}
	@RequestMapping(value = "winkelwagen/product/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id) {
		winkelwagenProductRepo.deleteById(id);
	}

	}
	


