package com.example.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Winkelwagen;

public interface IWinkelwagenRepository extends JpaRepository<Winkelwagen, Long> {

}
