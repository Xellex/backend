package com.example.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.LocalStorageDTO;
import com.example.backend.model.Klant;
import com.example.backend.model.Token;
import com.example.backend.model.Winkelier;
import com.example.backend.repo.IKlantenRepository;
import com.example.backend.repo.IWinkelierRepository;

@RestController
public class KlantenController {

	public final static int TOKENLENGTH = 20;

	@Autowired
	private IKlantenRepository Klantrepo;

	@Autowired
	private IWinkelierRepository Winkelierrepo;

	@RequestMapping(value = "klanten/aanmaken", method = RequestMethod.POST)
    public String create(@RequestBody Klant klant) {
        try {
            Klantrepo.save(klant);
            return "goed";
        } catch (Exception e) {
        	
        	System.out.println(e.toString());
            return e.toString();
        }
    }


	@RequestMapping(value = "klant/registreren", method = RequestMethod.POST)
	public void registreren(@RequestBody Klant klanten) {
		Klantrepo.save(klanten);
	}

	@GetMapping("klanten/id/{id}")
	public Klant klantById(@PathVariable int id) {
		Klant klant = Klantrepo.findById(id).get();
		return klant;
	}

	@GetMapping("klanten/inloggen")
	public LocalStorageDTO checkEmailAndPassword(@RequestParam String email, @RequestParam String password) {

		Optional<Winkelier> winkelierOptional = Winkelierrepo.findByEmailAndPassword(email, password);
		Optional<Klant> klantOptional = Klantrepo.findByEmailAndPassword(email, password);
		
		if (winkelierOptional.isPresent()) {
			
			Winkelier winkelier = winkelierOptional.get();
			Token token = new Token(TOKENLENGTH, "ADMIN");
			winkelier.setToken(token);
			Winkelierrepo.save(winkelier);
			return new LocalStorageDTO(true, winkelier.getName(), token, token.getRole());	
		}
		
		if (klantOptional.isPresent()) {
			
			Klant klant = klantOptional.get();
			Token token = new Token(TOKENLENGTH);
			klant.setToken(token);
			Klantrepo.save(klant);
			return new LocalStorageDTO(true, klant.getNaam(), token, token.getRole());	
		}
		
		return new LocalStorageDTO(false);
	}

	@GetMapping("/check-password")
	public boolean checkPassword(@RequestParam("password") String password) {
		Klant klant = Klantrepo.findByPassword(password);
		if (klant != null) {
			return true;
		} else {
			return false;
		}
	}
}
