package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {


	@RequestMapping(value = "status")
	public String status() {
		return "OK";
	}

}