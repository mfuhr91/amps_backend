package com.mutual.amps.localidades.providers;

import java.util.List;

import com.mutual.amps.localidades.models.Localidad;

import org.springframework.transaction.annotation.Transactional;

public interface ILocalidadService {
    
    
    @Transactional(readOnly = true)
    public List<Localidad>  listarTodo();
}
