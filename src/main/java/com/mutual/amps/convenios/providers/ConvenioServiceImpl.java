package com.mutual.amps.convenios.providers;

import java.util.List;

import com.mutual.amps.categorias.models.Categoria;
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

    @Override
    public List<Convenio> buscar(String param) {
        return this.convenioRepo.findByParam(param);
    }

    @Override
    public Integer contarConvenios() {
        return this.convenioRepo.contarConvenios();
    }

    @Override
    public List<Convenio> buscarPorCategoria(Categoria categoria) {
        return this.convenioRepo.findByCategoria(categoria);
    }
    
}
