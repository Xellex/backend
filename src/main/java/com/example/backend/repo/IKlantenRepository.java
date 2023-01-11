package com.example.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Klant;

public interface IKlantenRepository extends JpaRepository<Klant, Integer>{

	public boolean existsByEmail(String email);
	public Klant findByPassword(String password);



		
}
