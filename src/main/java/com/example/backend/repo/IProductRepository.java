package com.example.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{

}
