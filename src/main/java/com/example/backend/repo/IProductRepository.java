package com.example.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Product;
import com.example.backend.model.ProductCategorie;

public interface IProductRepository extends JpaRepository<Product, Long>{
	public List<Product> findAllByCategorie(ProductCategorie categorie);
}
