package com.mutual.amps.usuarios.models.repo;

import com.mutual.amps.usuarios.models.Rol;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepo extends JpaRepository<Rol, Integer> {
    
}
