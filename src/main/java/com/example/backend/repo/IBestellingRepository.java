package com.example.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Bestelling;

	public interface IBestellingRepository extends JpaRepository<Bestelling, Integer>{

	}

