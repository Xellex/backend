package com.example.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.WinkelwagenProduct;

public interface IWinkelwagenRepository extends JpaRepository<WinkelwagenProduct, Long> {

}
