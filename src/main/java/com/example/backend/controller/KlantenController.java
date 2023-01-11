package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Klant;
import com.example.backend.repo.IKlantenRepository;

@RestController
public class KlantenController {

	@Autowired
	private IKlantenRepository repo;

	@RequestMapping(value = "klanten/aanmaken", method = RequestMethod.POST)
	public void create(@RequestBody Klant klanten) {
		repo.save(klanten);
	}
	
	// Registreren
	@RequestMapping(value = "klant/registreren", method = RequestMethod.POST)
	public void registreren(@RequestBody Klant klanten) {
		repo.save(klanten);
	}

//		Klant storedUserDetails = repo.findByEmail(Klant.getEmail());
//		if(storedUserDetails != null) throw new RuntimeException("Record already exists");

	@GetMapping("klanten/id/{id}")
	public Klant klantById(@PathVariable int id) {
		Klant klant = repo.findById(id).get();
		return klant;
	}
	
//	public boolean exist (String email){
//		return repo.existByEmail(email);
//		
//	}
	@GetMapping("klanten/email/{email}")
    public boolean checkEmailExists(@PathVariable("email") String email) {
        if(repo.existsByEmail(email))
            return true;
        else
            return false;
    }

    @GetMapping("/check-password")
    public boolean checkPassword(@RequestParam("password") String password) {
        Klant klant = repo.findByPassword(password);
        if (klant != null) {
            return true;
        } else {
            return false;
        }
    }
}

//	    @GetMapping("/exists/{username}")
//	    public ResponseEntity<Boolean> checkNaamEntity(@PathVariable("naam") String naam) {
//	        boolean exists = IKlantenRepository.existsByNaam(naam);
//	        return ResponseEntity.ok(exists);
//	    }
