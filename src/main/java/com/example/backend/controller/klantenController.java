package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Klant;
import com.example.backend.repo.IKlantenRepository;

@RestController
public class klantenController {

	@Autowired
	private IKlantenRepository repo;

	@RequestMapping(value = "klanten/aanmaken", method = RequestMethod.POST)
	public void create(@RequestBody Klant klanten) {
		repo.save(klanten);
		
	}
	


	
	
}
