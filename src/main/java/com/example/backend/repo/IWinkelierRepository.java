package com.example.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Winkelier;

public interface IWinkelierRepository extends JpaRepository<Winkelier, Integer>{
	
	public Optional<Winkelier> findByEmailAndPassword(String email, String Password);
}