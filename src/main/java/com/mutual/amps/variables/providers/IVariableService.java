package com.mutual.amps.variables.providers;

import java.util.List;

import com.mutual.amps.variables.models.Variable;

import org.springframework.transaction.annotation.Transactional;



public interface IVariableService {
    
    @Transactional(readOnly = true)
    public List<Variable> listarTodo();
    
    @Transactional(readOnly = true)
    public Variable buscarPorId(Integer id);

    @Transactional(readOnly = true)
    public Variable buscarPorNombre(String nombre);

    /* public void guardar(Variable socio); */

}
