package com.example.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Klant;
import com.example.backend.model.Winkelwagen;

public interface IWinkelwagenRepository extends JpaRepository<Winkelwagen, Long> {

public Optional<Winkelwagen> findByKlant(Klant klant);
}
