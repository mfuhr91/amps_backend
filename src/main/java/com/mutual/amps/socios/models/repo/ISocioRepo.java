package com.mutual.amps.socios.models.repo;

import java.util.List;

import com.mutual.amps.socios.models.Socio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocioRepo extends JpaRepository<Socio, Integer> {

    @Query(value = "SELECT * FROM socios ORDER BY apellido, nombre ASC", nativeQuery = true)
    public List<Socio> findAll();
    
    @Query(value = "SELECT * FROM socios WHERE baja = false ORDER BY apellido, nombre ASC", nativeQuery = true)
    public List<Socio> findAllNoBaja();

    @Query(value = "SELECT * FROM socios WHERE num_doc LIKE %:param% OR apellido LIKE %:param% OR nombre LIKE %:param% OR legajo LIKE %:param%", nativeQuery = true)
    public List<Socio> findByParam(@Param("param") String param);

    @Query(value = "SELECT COUNT(*) FROM socios WHERE baja = false", nativeQuery = true)
    public Integer contarSocios();

    public Socio findByNumDoc(Long numDoc);

}
