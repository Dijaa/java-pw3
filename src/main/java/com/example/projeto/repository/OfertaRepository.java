package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.models.OfertaModel;

public interface OfertaRepository extends JpaRepository<OfertaModel, Integer> {

    
}