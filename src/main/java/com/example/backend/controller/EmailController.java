package com.example.backend.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/sendemail")
	public void sendEmail() {
		String loremIpsum = "loremIpsum";

		this.emailService.sendSimpleMessage("noreply@demo.nl", "to@demo.nl", "Demo bericht", loremIpsum);
	}

	@GetMapping("/sendemailwithattachment")
	public void sendEmailWithAttachment() {
		String loremIpsum = "loremIpsum";

		try {
			this.emailService.sendMessageWithAttachment("noreply@demo.nl", "to@demo.nl", "Demo bericht", loremIpsum);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
