package com.mutual.amps.socios.models.repo;

import java.util.List;

import com.mutual.amps.socios.models.Socio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocioRepo extends JpaRepository<Socio, Integer> {

    @Query(value = "SELECT * FROM socios WHERE num_doc LIKE %:param% OR apellido LIKE %:param% OR nombre LIKE %:param% OR legajo LIKE %:param%", nativeQuery = true)
    public List<Socio> findByParam(@Param("param") String param);

    @Query(value = "SELECT COUNT(*) FROM socios", nativeQuery = true)
    public Integer contarSocios();

}
