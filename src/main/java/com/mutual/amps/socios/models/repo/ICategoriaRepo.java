package com.mutual.amps.socios.models.repo;

import com.mutual.amps.socios.models.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepo extends JpaRepository<Categoria, Integer>{}
