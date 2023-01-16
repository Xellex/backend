package com.example.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Klant;
import com.example.backend.model.Token;
import com.example.backend.model.Winkelier;

public interface ITokenRepository extends JpaRepository<Token, Integer>{
	public Optional<Token> findByRandomstring(String randomstring);

	public Optional<Token> findByKlant(Klant klant);
	public Optional<Token> findByWinkelier(Winkelier winkelier);
}
