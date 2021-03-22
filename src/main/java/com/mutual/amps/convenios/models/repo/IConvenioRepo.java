package com.mutual.amps.convenios.models.repo;

import java.util.List;

import com.mutual.amps.categorias.models.Categoria;
import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.usuarios.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IConvenioRepo extends JpaRepository<Convenio, Integer>{

    @Query(value = "SELECT * FROM convenios ORDER BY nombre ASC", nativeQuery = true)
    public List<Convenio> findAll();

    @Query(value = "SELECT * FROM convenios WHERE baja = false ORDER BY nombre ASC", nativeQuery = true)
    public List<Convenio> findAllNoBaja();

    @Query(value = "SELECT * FROM convenios WHERE cuit LIKE %:param% OR contacto LIKE %:param% OR nombre LIKE %:param% OR telefono LIKE %:param% OR direccion LIKE %:param%", nativeQuery = true)
    public List<Convenio> findByParam(@Param("param") String param);

    @Query(value = "SELECT COUNT(*) FROM convenios WHERE baja = false", nativeQuery = true)
    public Integer contarConvenios();

    public List<Convenio> findByCategoria(Categoria categoria);

    public Convenio findByUsuario(Usuario usuario);


}
