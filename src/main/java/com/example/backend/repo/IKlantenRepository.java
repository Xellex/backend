package com.example.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Klanten;

public interface IKlantenRepository extends JpaRepository<Klanten, Integer>{
		
}
