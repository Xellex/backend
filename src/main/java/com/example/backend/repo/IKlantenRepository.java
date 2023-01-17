 package com.example.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Klant;

public interface IKlantenRepository extends JpaRepository<Klant, Integer>{

	public boolean existsByEmail(String email);
	public Klant findByPassword(String password);
	
	public Optional<Klant> findByEmailAndPassword(String email, String Password);
}
