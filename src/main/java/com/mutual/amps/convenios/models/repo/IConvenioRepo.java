package com.mutual.amps.convenios.models.repo;
import com.mutual.amps.convenios.models.Convenio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConvenioRepo extends JpaRepository<Convenio, Integer>{

}
