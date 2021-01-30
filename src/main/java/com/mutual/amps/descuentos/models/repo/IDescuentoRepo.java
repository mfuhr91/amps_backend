package com.mutual.amps.descuentos.models.repo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.descuentos.models.Descuento;
import com.mutual.amps.socios.models.Socio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDescuentoRepo extends JpaRepository<Descuento, Long>{

    public List<Descuento> findBySocio(Socio socio);

    public List<Descuento> findByConvenio(Convenio convenio);

    @Query(value = "SELECT * FROM descuentos AS d join cuotas AS c where fecha_cuota LIKE %:param% AND d.id = c.descuento_id", nativeQuery = true)
    public List<Descuento> getDescuentosByFechaCuota(@Param("param") String param);

    public List<Descuento> findByDescripcion(String descripcion);

    @Query(value = "SELECT * FROM descuentos where fecha_alta > %:param%", nativeQuery = true)
    public List<Descuento> findByFechaAltaBiggerThan(@Param("param") Date fechaAlta);

    @Query( value= "SELECT SUM(valor_total) FROM descuentos", nativeQuery = true)
    public Double sumarTotalRecaudado();


    
}
