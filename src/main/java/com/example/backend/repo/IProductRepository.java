package com.example.backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Product;
import com.example.backend.model.WinkelwagenProduct;

public interface IProductRepository extends JpaRepository<Product, Long>{

}
