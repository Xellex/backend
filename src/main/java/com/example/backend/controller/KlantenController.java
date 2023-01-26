package com.example.backend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.AdminKlantDTO;
import com.example.backend.dto.KlantDTO;
import com.example.backend.dto.LoginResponseDTO;
import com.example.backend.dto.ProductDTO;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.dto.VerlanglijstProductDTO;
import com.example.backend.model.Klant;
import com.example.backend.model.Product;
import com.example.backend.model.Token;
import com.example.backend.model.VerlanglijstProduct;
import com.example.backend.model.Winkelier;
import com.example.backend.repo.IKlantenRepository;
import com.example.backend.repo.IProductRepository;
import com.example.backend.repo.ITokenRepository;
import com.example.backend.repo.IVerlanglijstProductRepository;
import com.example.backend.repo.IWinkelierRepository;
import com.example.backend.service.AuthenticationService;

@RestController
public class KlantenController {

	public final static int TOKENLENGTH = 20;

	@Autowired
	private IKlantenRepository klantRepo;

	@Autowired
	private IProductRepository productRepo;

	@Autowired
	private IWinkelierRepository winkelierRepo;

	@Autowired
	private ITokenRepository tokenRepo;

	@Autowired
	private IVerlanglijstProductRepository verlanglijstRepo;

	@Autowired
	private AuthenticationService authService;

	@RequestMapping(value = "klanten/aanmaken", method = RequestMethod.POST)
	public ResponseDTO create(@RequestBody Klant klant) {
		try {
			klantRepo.save(klant);
			return new ResponseDTO(true);
		} catch (Exception e) {
			return new ResponseDTO(false);
		}

	}

	@PostMapping(value = "klant/registreren")
	public ResponseDTO registreren(@RequestBody Klant klant) {
		try {
			klantRepo.save(klant);
			return new ResponseDTO(true);
		} catch (Exception e) {
			ArrayList<String> errors = new ArrayList<String>();
			errors.add(e.toString());
			return new ResponseDTO(false, errors);
		}
	}

	@GetMapping("klanten/id/{id}")
	public boolean klantById(@PathVariable int id, @RequestHeader("Authentication") String authenticationToken) {
		boolean rights = authService.doesTokenHaveRole(authenticationToken, "WINKELIER");
		if (rights) {
			// Klant klant = klantRepo.findById(id).get();
			return true;
		}
		return false;
	}

	@GetMapping("klanten/all")
	public ArrayList<AdminKlantDTO> klantById(@RequestHeader("Authentication") String authenticationToken) {
		boolean rights = authService.doesTokenHaveRole(authenticationToken, "WINKELIER");
		if (rights) {
			ArrayList<AdminKlantDTO> klant = new ArrayList<AdminKlantDTO>();
			ArrayList<Klant> arrayList = new ArrayList<>(klantRepo.findAll());
			for (Klant k : arrayList) {
				AdminKlantDTO i = new AdminKlantDTO();
				i.setAdres(k.getAdres());
				i.setEmail(k.getEmail());
				i.setNaam(k.getNaam());
				i.setTelefoonnummer(k.getTelefoonnummer());
				klant.add(i);
			}
			return klant;
		}
		return null;
	}

	@PostMapping("klanten/inloggen")
	public LoginResponseDTO checkEmailAndPassword(@RequestBody KlantDTO klant) {
		ArrayList<String> errors = new ArrayList<String>();

		try {

			var email = klant.getUsername();
			var password = klant.getPassword();
			Winkelier winkelierDB = winkelierRepo.findByEmailAndPassword(email, password).orElse(null);
			Klant klantDB = klantRepo.findByEmailAndPassword(email, password).orElse(null);

			if (winkelierDB != null) {
				Token tokenDB = tokenRepo.findByWinkelier(winkelierDB).orElse(null);
				if (tokenDB != null) {
					// Do something with the found token
					tokenDB.setCreationtime(System.currentTimeMillis());
					tokenRepo.save(tokenDB);
					return new LoginResponseDTO(tokenDB.getRandomstring(), tokenDB.getRole(), null,
							winkelierDB.getName());
				} else {
					// Handle case where no token is found for this winkelier
					Token token = new Token(TOKENLENGTH, "WINKELIER");
					token.setWinkelier(winkelierDB);
					tokenRepo.save(token);
					return new LoginResponseDTO(token.getRandomstring(), token.getRole(), null, winkelierDB.getName());
				}
			}

			if (klantDB != null) {
				Token tokenDB = tokenRepo.findByKlant(klantDB).orElse(null);
				if (tokenDB != null) {
					// Do something with the found token
					tokenDB.setCreationtime(System.currentTimeMillis());
					tokenRepo.save(tokenDB);
					return new LoginResponseDTO(tokenDB.getRandomstring(), tokenDB.getRole(), null, klantDB.getNaam());
				} else {
					// Handle case where no token is found for this klant
					Token token = new Token(TOKENLENGTH);
					token.setKlant(klantDB);
					tokenRepo.save(token);
					return new LoginResponseDTO(token.getRandomstring(), token.getRole(), null, klantDB.getNaam());
				}
			} else {
				// Handle case where no klant is found with the given email and password
				errors.add("wachtwoord of email is fout");
				return new LoginResponseDTO(null, null, errors, null);

			}
		} catch (Exception e) {
			errors.add(e.toString());
			return new LoginResponseDTO(null, null, errors, null);
		}
	}

//		if (winkelierOptional.isPresent()) {
//			
//			Winkelier winkelier = winkelierOptional.get();
//			Token token = new Token(TOKENLENGTH, "ADMIN");
//			winkelier.setToken(token);
//			Winkelierrepo.save(winkelier);
//			//return new LocalStorageDTO(true, winkelier.getName(), token, token.getRole());
//			//return token;
//		}

	@GetMapping("/check-password")
	public boolean checkPassword(@RequestParam("password") String password) {
		Klant klant = klantRepo.findByPassword(password);
		if (klant != null) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("klant/favorieten")
	public ArrayList<ProductDTO> getMyVariableValues(@RequestHeader("Authentication") String authenticationToken) {
		Optional<Token> optionalToken = authService.findByToken(authenticationToken);
		Klant klant = optionalToken.get().getKlant();

		List<VerlanglijstProduct> verlanglijstproducten = verlanglijstRepo.findByKlant(klant);
		ArrayList<ProductDTO> producten = new ArrayList<ProductDTO>();
		for (VerlanglijstProduct verlanglijstproduct : verlanglijstproducten) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(verlanglijstproduct.getProduct().getId());
			productDTO.setBeschrijving(verlanglijstproduct.getProduct().getBeschrijving());
			productDTO.setCategorie(verlanglijstproduct.getProduct().getCategorie());
			productDTO.setFeestdag(verlanglijstproduct.getProduct().getFeestdag());
			productDTO.setInkoop(verlanglijstproduct.getProduct().getInkoop());
			productDTO.setKosten(verlanglijstproduct.getProduct().getKosten());
			productDTO.setNaam(verlanglijstproduct.getProduct().getNaam());
			productDTO.setVoorraad(verlanglijstproduct.getProduct().getVoorraad());
			productDTO.setAfbeelding(verlanglijstproduct.getProduct().getAfbeelding());
			producten.add(productDTO);
		}
		return producten;
	}

	@PostMapping("klant/favoriet/toevoegen")
	public ResponseDTO toevoegenFavoriet(@RequestBody VerlanglijstProductDTO dto,
			@RequestHeader("Authentication") String authenticationToken) {
		Optional<Token> optionalToken = authService.findByToken(authenticationToken);
		if (optionalToken.isEmpty())
			return new ResponseDTO(false, "No token");

		boolean rights = authService.doesTokenHaveRole(authenticationToken, "KLANT");
		if (!rights)
			return new ResponseDTO(false, "Geen rechten");

		Klant klant = optionalToken.get().getKlant();

		VerlanglijstProduct verlanglijstProduct = new VerlanglijstProduct();

		verlanglijstProduct.setDatumToegevoegd(LocalDateTime.now());
		verlanglijstProduct.setKlant(klant);
		Product productdb = productRepo.findById(dto.getProductId()).orElse(null);
		VerlanglijstProduct verlanglijstProductDB = verlanglijstRepo.findByProductAndKlant(productdb, klant)
				.orElse(null);

		if (productdb != null) {
			if (verlanglijstProductDB != null)
				return new ResponseDTO(false, "Product zit al in favorieten");
			verlanglijstProduct.setProduct(productdb);
			verlanglijstRepo.save(verlanglijstProduct);
			return new ResponseDTO(true);
		}
		return new ResponseDTO(false, "product bestaat niet");
	}

	@PostMapping("klant/favoriet/verwijderen")
	public ResponseDTO verwijderenFavoriet(@RequestBody VerlanglijstProductDTO dto,
			@RequestHeader("Authentication") String authenticationToken) {
		Optional<Token> optionalToken = authService.findByToken(authenticationToken);
		if (optionalToken.isEmpty())
			return new ResponseDTO(false, "No token");

		boolean rights = authService.doesTokenHaveRole(authenticationToken, "KLANT");
		if (!rights)
			return new ResponseDTO(false, "Geen rechten");

		Klant klant = optionalToken.get().getKlant();
		Product productdb = productRepo.findById(dto.getProductId()).orElse(null);

		if (productdb != null) {
			VerlanglijstProduct verlanglijstProduct = verlanglijstRepo.findByProductAndKlant(productdb, klant)
					.orElse(null);
			if (verlanglijstProduct != null) {
				verlanglijstRepo.delete(verlanglijstProduct);
				return new ResponseDTO(true, "verlanglijstProduct bestaat niet");
			}
			return new ResponseDTO(false, "verlanglijstProduct bestaat niet");
		}
		return new ResponseDTO(false, "product bestaat niet");
	}
}
