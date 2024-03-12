package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.models.ImovelModel;

public interface ImovelRepository extends JpaRepository<ImovelModel, Integer> {

    
}