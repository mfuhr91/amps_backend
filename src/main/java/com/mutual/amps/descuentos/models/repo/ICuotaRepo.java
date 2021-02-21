package com.mutual.amps.descuentos.models.repo;

import java.util.List;

import com.mutual.amps.descuentos.models.Cuota;
import com.mutual.amps.descuentos.models.Descuento;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ICuotaRepo extends JpaRepository<Cuota, Long> {

    public List<Cuota> findByDescuento(Descuento descuento);
    
}
