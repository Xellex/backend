package com.example.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class klantenController {

	@Autowired
	private IKlantenRepository repo;

	@RequestMapping(value = "klanten/aanmaken", method = RequestMethod.POST)
	public void create(@RequestBody Klanten klanten) {
		repo.save(klanten);
		
	}
	


}
