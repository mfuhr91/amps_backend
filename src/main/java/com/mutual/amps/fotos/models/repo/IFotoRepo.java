package com.mutual.amps.fotos.models.repo;

import com.mutual.amps.fotos.models.Foto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFotoRepo extends JpaRepository<Foto, Integer>{
    
}
