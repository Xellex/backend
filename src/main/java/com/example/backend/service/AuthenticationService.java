package com.example.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.Token;
import com.example.backend.repo.ITokenRepository;

@Service
public class AuthenticationService {

	@Autowired
	private ITokenRepository repo;
	
	public Optional<Token> findByToken(String token) {
		return repo.findByRandomstring(token);
	}
	
	public boolean doesTokenHaveRole(String authenticationToken, String expectedRole) {
		// Vind de token
		Optional<Token> tokenOptional = repo.findByRandomstring(authenticationToken);

		// Als geen token gevonden dan geef unauthorized
		if (tokenOptional.isEmpty()) {
			return false;
		}

		// Token is gevonden en nu kunnen we dus de rollen gaan checken
		Token token = tokenOptional.get();
		
		// Tijd checken
//		if (token.isExpired())
//			return false;
		
		return (expectedRole.equals(token.getRole()));
	}
	
	
}
