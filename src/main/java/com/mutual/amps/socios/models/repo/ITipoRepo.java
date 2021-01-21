package com.mutual.amps.socios.models.repo;

import com.mutual.amps.socios.models.Tipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoRepo extends JpaRepository<Tipo, Integer>{
    
}
