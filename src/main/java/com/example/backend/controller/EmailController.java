package com.example.backend.controller;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.ResponseDTO;
import com.example.backend.model.Email;
import com.example.backend.service.EmailService;

@RestController
public class EmailController {

	public static String mijnEmail = "info.freddyfeestwinkel@gmail.com";

	@Autowired
	private EmailService emailService;

	@PostMapping("/email/send")
	public ResponseDTO send(@RequestBody Email email) {
	    ResponseDTO responseDTO = new ResponseDTO();

	    ArrayList<String> validaties = new ArrayList<>();

	    try {
	    	// adding phone to the message body
	    	emailService.sendSimpleMessage(mijnEmail, email.getSubject(), 
	    	email.getMessage() + " telefoonnummer klant:" + email.getPhone());
	        responseDTO.setSucces(true);
	    } catch (Exception e) {
	        // add the exception message to the list
	        validaties.add(e.getMessage());
	        responseDTO.setSucces(false);
	        
	        // check if there are any additional messages
	        if (e.getCause() instanceof MessagingException) {
	            MessagingException me = (MessagingException) e.getCause();
	            validaties.add(me.getMessage());
	        }
	        if (e.getCause() instanceof AddressException) {
	            AddressException ae = (AddressException) e.getCause();
	            validaties.add(ae.getMessage());
	        }
	        // add the list of validation messages to the response DTO
	        responseDTO.setValidaties(validaties);
	    }
	    
	    //send confirmation email back
	    String subject = new String();
	    String message = new String();
	    message = email.getFirstname()+ " " + email.getLastname() + ",\nBedankt voor uw bericht."
	    		+ " Wij hebben uw bericht in goede orde ontvangen."
	    		+ " Wij proberen zo spoedig mogelijk ue bericht te beantworden."
	    		+ "\nUw bericht: \n" + email.getMessage();
	    subject = "RE/" + email.getSubject();
	    
	    emailService.sendSimpleMessage(email.getTo(), subject, message);
	    
	    return responseDTO;
	}
}
