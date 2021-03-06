package com.mutual.amps.convenios.providers;

import java.util.List;

import com.mutual.amps.convenios.models.Convenio;

import org.springframework.transaction.annotation.Transactional;

public interface IConvenioService {

    @Transactional(readOnly = true)
    public List<Convenio> listarTodo();
    
    @Transactional(readOnly = true)
    public Convenio buscarPorId(Integer id);

    public void guardar(Convenio convenio);

    public void eliminar(Integer id);
    
}
