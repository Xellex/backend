package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Person;
import com.example.backend.Winkelwagen;
import com.example.backend.repo.IWinkelwagenRepository;

import java.util.List;

@RestController
public class StatusController {
	
	@Autowired
	private IWinkelwagenRepository repo;
	
	@RequestMapping(value = "winkelwagens")
	public List<Winkelwagen> vindAlleWinkelwagens() {
		return repo.findAll();
	}

	@RequestMapping(value = "status")
	public String status() {
		return "OK";
	}

	@RequestMapping(value = "person")
	public Person person() {
		Person p = new Person();
		p.setFirstName("Jorn");
		p.setLastName("Bouwmeester");
		return p;
	}
}