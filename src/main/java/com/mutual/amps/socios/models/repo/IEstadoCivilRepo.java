package com.mutual.amps.socios.models.repo;

import com.mutual.amps.socios.models.EstadoCivil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoCivilRepo extends JpaRepository<EstadoCivil, Integer>{}
