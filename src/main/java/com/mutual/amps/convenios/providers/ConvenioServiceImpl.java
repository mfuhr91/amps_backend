package com.mutual.amps.convenios.providers;

import java.util.List;

import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.convenios.models.repo.IConvenioRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConvenioServiceImpl implements IConvenioService {

    @Autowired
    private IConvenioRepo convenioRepo;

    @Override
    public List<Convenio> listarTodo() {
        return this.convenioRepo.findAll();
    }

    @Override
    public Convenio buscarPorId(Integer id) {
       return this.convenioRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Convenio convenio) {
        this.convenioRepo.save(convenio);

    }

    @Override
    public void eliminar(Integer id) {
        this.convenioRepo.deleteById(id);

    }
    
}
