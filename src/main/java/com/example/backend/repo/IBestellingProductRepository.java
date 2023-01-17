package com.example.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.BestellingProduct;

	public interface IBestellingProductRepository extends JpaRepository<BestellingProduct, Integer>{

	}

