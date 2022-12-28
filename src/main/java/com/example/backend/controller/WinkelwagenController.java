package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Winkelwagen;
import com.example.backend.repo.IWinkelwagenRepository;

@RestController
public class WinkelwagenController {

	@Autowired
	private IWinkelwagenRepository repo;

	@RequestMapping(value = "winkelwagen/aanmaken", method = RequestMethod.POST)
	public void create(@RequestBody Winkelwagen winkelwagen) {
		repo.save(winkelwagen);

	}
	@RequestMapping(value="winkelwagen/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable long id, @RequestBody Winkelwagen winkelwagen) {
		//database winkelwagen
		Winkelwagen ww = repo.findById(id).get();
		
		
		// update de db winkelwagen met winkelwagen uit de request body
		ww.setHoeveelheid(winkelwagen.getHoeveelheid());
		ww.setAangemaakt(winkelwagen.getAangemaakt());
		//save the database winkelwagen
		repo.save(ww);
	} 	
	
	@RequestMapping(value="winkelwagen/verwijderen/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id) {
		repo.deleteById(id);
	} 	
	
	@RequestMapping (value = "winkelwagens/opvragen/{id}")
	public Winkelwagen winkelwagenid(@PathVariable long id){
		return repo.findById(id).get();
	}
}