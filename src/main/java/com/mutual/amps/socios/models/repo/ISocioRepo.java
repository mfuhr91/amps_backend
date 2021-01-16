package com.mutual.amps.socios.models.repo;

import com.mutual.amps.socios.models.Socio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocioRepo extends JpaRepository<Socio, Integer> {}
