package com.example.backend.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.ProductDTO;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.dto.WinkelwagenDTO;
import com.example.backend.dto.WinkelwagenProductDTO;
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
import com.example.backend.repo.IProductRepository;
import com.example.backend.repo.IWinkelwagenProductRepository;
import com.example.backend.repo.IWinkelwagenRepository;
import com.example.backend.service.AuthenticationService;

@RestController
public class WinkelwagenController {

	@Autowired
	private IWinkelwagenRepository winkelwagenrepo;
	
	@Autowired
	private IProductRepository productrepo;

	@Autowired
	private IWinkelwagenProductRepository winkelwagenproductrepo;

	@Autowired
	private IBestellingRepository bestellingrepo;

	@Autowired
	private IBestellingProductRepository bestellingproductrepo;

	@Autowired
	private AuthenticationService authService;

	@PostMapping("winkelwagen/product/toevoegen")
	public ResponseDTO aanmaken(@RequestBody WinkelwagenProductDTO dto,
			@RequestHeader("Authentication") String authenticationToken) {
		Optional<Token> optionalToken = authService.findByToken(authenticationToken);
		if (optionalToken.isEmpty())
			return new ResponseDTO(false, "No token");

		boolean rights = authService.doesTokenHaveRole(authenticationToken, "KLANT");
		if (!rights)
			return new ResponseDTO(false, "Geen rechten");

		Klant klant = optionalToken.get().getKlant();

		// vinden van het product uit de productID
		Product productdb = productrepo.findById(dto.getProductId()).orElse(null);

		if (productdb != null) {

			// vinden van winkelwagen bij de klant
			Winkelwagen ww = klant.getWinkelwagen();
			if (ww == null) {
				Winkelwagen wwnew = new Winkelwagen();
				wwnew.setKlant(klant);
				wwnew.setDatumToegevoegd(LocalDateTime.now());
				winkelwagenrepo.save(wwnew);
				ww = wwnew;
			}

			WinkelwagenProduct wwpdb = winkelwagenproductrepo.findByWinkelwagenAndProduct(ww, productdb).orElse(null);
			if (wwpdb == null) {
				WinkelwagenProduct winkelwagenProduct = new WinkelwagenProduct();
				winkelwagenProduct.setProduct(productdb);
				winkelwagenProduct.setHoeveelheid(1);
				winkelwagenProduct.setAangemaakt(LocalDate.now());
				winkelwagenProduct.setWinkelwagen(ww);

				winkelwagenproductrepo.save(winkelwagenProduct);
				return new ResponseDTO(true);
			} else {
				wwpdb.setHoeveelheid(wwpdb.getHoeveelheid() + 1);
				winkelwagenproductrepo.save(wwpdb);
				return new ResponseDTO(true);
			}
		}
		return new ResponseDTO(false, "product bestaat niet");
	}

	@PostMapping("winkelwagen/product/verwijderen")
	public ResponseDTO verwijderen(@RequestBody WinkelwagenProductDTO dto,
			@RequestHeader("Authentication") String authenticationToken) {
		Optional<Token> optionalToken = authService.findByToken(authenticationToken);
		if (optionalToken.isEmpty())
			return new ResponseDTO(false, "No token");

		boolean rights = authService.doesTokenHaveRole(authenticationToken, "KLANT");
		if (!rights)
			return new ResponseDTO(false, "Geen rechten");

		Klant klant = optionalToken.get().getKlant();

		// vinden van het product uit de productID
		Product productdb = productrepo.findById(dto.getProductId()).orElse(null);

		if (productdb != null) {

			// vinden van winkelwagen bij de klant
			Winkelwagen ww = klant.getWinkelwagen();
			if (ww == null)
				return new ResponseDTO(false, "Product zit niet in winkelwagen");

			WinkelwagenProduct wwpdb = winkelwagenproductrepo.findByWinkelwagenAndProduct(ww, productdb).orElse(null);
			if (wwpdb == null) {
				return new ResponseDTO(false, "Product zit niet in winkelwagen");
			} else {
				if (wwpdb.getHoeveelheid() == 0)
					return new ResponseDTO(false, "Product zit niet in winkelwagen");
				wwpdb.setHoeveelheid(wwpdb.getHoeveelheid() - 1);
				winkelwagenproductrepo.save(wwpdb);
				return new ResponseDTO(true);
			}
		}
		return new ResponseDTO(false, "product bestaat niet");
	}

	@PostMapping("winkelwagen/bestellen")
	public ResponseDTO bestellen(@RequestHeader("Authentication") String authenticationToken) {
		Optional<Token> optionalToken = authService.findByToken(authenticationToken);
		if (optionalToken.isEmpty())
			return new ResponseDTO(false, "No token");

		//boolean rights = authService.doesTokenHaveRole(authenticationToken, "KLANT");

		Klant klant = optionalToken.get().getKlant();

		Winkelwagen ww = klant.getWinkelwagen();
		
		List<WinkelwagenProduct> winkelwagenproductList = winkelwagenproductrepo.findByWinkelwagen(ww);
		
	
		
		if (!winkelwagenproductList.isEmpty()) {

			List<BestellingProduct> bestellingproducten = new ArrayList<BestellingProduct>();

			for (WinkelwagenProduct winkelwagenProduct : winkelwagenproductList) {
				Product product = winkelwagenProduct.getProduct();
				
				BestellingProduct bestellingProduct = new BestellingProduct();
				bestellingProduct.setProduct(product);
				bestellingProduct.setHoeveelheid(winkelwagenProduct.getHoeveelheid());
				bestellingProduct.setInkoop(product.getInkoop());
				bestellingProduct.setKosten(product.getKosten());
				bestellingproductrepo.save(bestellingProduct);
				bestellingproducten.add(bestellingProduct);
			}
			Bestelling bestelling = new Bestelling();
			bestelling.setBestellingproducten(bestellingproducten);
			bestelling.setDateCreated(LocalDateTime.now());
			bestelling.setBestellingstatus(Bestellingstatus.CREATED);
			bestelling.setKlant(klant);
			for (BestellingProduct bestellingproduct : bestelling.getBestellingproducten()) {
				bestellingproduct.setBestelling(bestelling);
			}
			bestellingrepo.save(bestelling);
			
			return new ResponseDTO(true);
		}
		return new ResponseDTO(false, "geen producten in winkelmand");
	}

	@GetMapping("winkelwagen/klant")
	public WinkelwagenDTO getMyVariableValues(@RequestHeader("Authentication") String authenticationToken) {

		Optional<Token> optionalToken = authService.findByToken(authenticationToken);

		// klant uit token
		Klant klant = optionalToken.get().getKlant();
		Winkelwagen ww = klant.getWinkelwagen();
		List<WinkelwagenProduct> wwpddb = winkelwagenproductrepo.findByWinkelwagen(ww);

		WinkelwagenDTO dto1 = new WinkelwagenDTO();
		for (WinkelwagenProduct wwpd : wwpddb) {
			ProductDTO p = new ProductDTO();
			Product i = wwpd.getProduct();
			p.setBeschrijving(i.getBeschrijving());
			p.setCategorie(i.getCategorie());
			p.setKosten(i.getKosten());
			p.setNaam(i.getNaam());
			dto1.addProduct(p, wwpd.getHoeveelheid());
		}
		return dto1;
	}

}
