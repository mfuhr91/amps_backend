package com.mutual.amps.localidades.providers;

import java.util.List;

import com.mutual.amps.localidades.models.Localidad;
import com.mutual.amps.localidades.models.repo.ILocalidadRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadServiceImpl implements ILocalidadService {

    @Autowired
    private ILocalidadRepo localidadRepo;

    @Override
    public List<Localidad> listarTodo() {
        return this.localidadRepo.findAll();
    }
}
