package com.example.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Product;

public interface IProductRepository extends JpaRepository<Product, Integer>{
	

}
