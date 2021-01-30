package com.mutual.amps.convenios.models.repo;

import java.util.List;

import com.mutual.amps.convenios.models.Convenio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IConvenioRepo extends JpaRepository<Convenio, Integer>{


    @Query(value = "SELECT * FROM convenios WHERE cuit LIKE %:param% OR contacto LIKE %:param% OR nombre LIKE %:param% OR telefono LIKE %:param% OR direccion LIKE %:param%", nativeQuery = true)
    public List<Convenio> findByParam(@Param("param") String param);

    @Query(value = "SELECT COUNT(*) FROM convenios", nativeQuery = true)
    public Integer contarConvenios();


}
