package com.mutual.amps.categorias.models.repo;

import java.util.List;

import com.mutual.amps.categorias.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepo extends JpaRepository<Categoria, Integer>{

    public Categoria findByNombre(String nombre);

    @Query(value = "SELECT * FROM categorias WHERE nombre LIKE %:param% AND id <> 1", nativeQuery = true)
    public List<Categoria> findByParam(@Param("param") String param);
}
