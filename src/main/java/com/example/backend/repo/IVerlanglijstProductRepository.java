package com.example.backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Klant;
import com.example.backend.model.Product;
import com.example.backend.model.VerlanglijstProduct;

public interface IVerlanglijstProductRepository extends JpaRepository<VerlanglijstProduct, Long> {
	public List<VerlanglijstProduct> findByKlant(Klant klant);	
	public Optional<VerlanglijstProduct> findByProductAndKlant(Product product, Klant klant);
}
