package com.example.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Klant;
import com.example.backend.model.Product;
import com.example.backend.model.Winkelwagen;
import com.example.backend.model.WinkelwagenProduct;

public interface IWinkelwagenProductRepository extends JpaRepository<WinkelwagenProduct, Long> {
public Optional<WinkelwagenProduct> findByWinkelwagenAndProduct(Winkelwagen winkelwagen, Product product);
	
}
