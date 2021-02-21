package com.mutual.amps.descuentos.models.repo;

import java.util.List;

import com.mutual.amps.descuentos.models.Descuento;
import com.mutual.amps.descuentos.models.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IItemRepo extends JpaRepository<Item, Long> {
    

    public List<Item> findByDescuento(Descuento descuento);

    
}
