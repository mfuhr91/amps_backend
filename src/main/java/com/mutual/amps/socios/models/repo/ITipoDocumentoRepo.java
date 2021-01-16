package com.mutual.amps.socios.models.repo;

import com.mutual.amps.socios.models.TipoDocumento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoDocumentoRepo extends JpaRepository<TipoDocumento, Integer>{}
