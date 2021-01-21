package com.mutual.amps.categorias.models.repo;

import com.mutual.amps.categorias.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepo extends JpaRepository<Categoria, Integer>{}
