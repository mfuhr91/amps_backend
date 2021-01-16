package com.mutual.amps.usuarios.models.repo;

import com.mutual.amps.usuarios.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {

}
