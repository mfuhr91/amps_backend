package com.mutual.amps.usuarios.models.repo;

import java.util.List;

import com.mutual.amps.usuarios.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM usuarios WHERE nombre_usuario LIKE %:param% AND id <> 1", nativeQuery = true)
    public List<Usuario> findByParam(@Param("param") String param);

    public List<Usuario> findByNombreUsuario(String nombreUsuario);
    


}
