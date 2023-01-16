package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.KlantDTO;
import com.example.backend.dto.LocalStorageDTO;
import com.example.backend.dto.LoginResponseDTO;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.model.Klant;
import com.example.backend.model.Token;
import com.example.backend.model.Winkelier;
import com.example.backend.repo.IKlantenRepository;
import com.example.backend.repo.ITokenRepository;
import com.example.backend.repo.IWinkelierRepository;
import com.example.backend.service.AuthenticationService;

@RestController
public class KlantenController {

	public final static int TOKENLENGTH = 20;

	@Autowired
	private IKlantenRepository klantRepo;

	@Autowired
	private IWinkelierRepository winkelierRepo;

	@Autowired
	private ITokenRepository tokenRepo;
	
	@Autowired
	private AuthenticationService authService;

	@RequestMapping(value = "klanten/aanmaken", method = RequestMethod.POST)
	public ResponseDTO create(@RequestBody Klant klant, @RequestHeader("Authentication") String authenticationToken) {
		boolean rights = authService.doesTokenHaveRole(authenticationToken, "WINKELIER");

		if (rights) {
			klantRepo.save(klant);

			return new ResponseDTO(true);
		} else 
			return new ResponseDTO(false, "Je hebt geen rechten");
	}

	@RequestMapping(value = "klant/registreren", method = RequestMethod.POST)
	public void registreren(@RequestBody Klant klanten) {
		klantRepo.save(klanten);
	}

	@GetMapping("klanten/id/{id}")
	public Klant klantById(@PathVariable int id) {
		Klant klant = klantRepo.findById(id).get();
		return klant;
	}

	@GetMapping("klanten/inloggen")
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
}
