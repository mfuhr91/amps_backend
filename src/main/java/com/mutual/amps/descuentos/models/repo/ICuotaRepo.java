package com.mutual.amps.descuentos.models.repo;



import com.mutual.amps.descuentos.models.Cuota;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ICuotaRepo extends JpaRepository<Cuota, Long> {

    
    
}
