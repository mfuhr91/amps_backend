package com.mutual.amps.localidades.models.repo;

import com.mutual.amps.localidades.models.Localidad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocalidadRepo extends JpaRepository<Localidad, Integer> {
    
}
