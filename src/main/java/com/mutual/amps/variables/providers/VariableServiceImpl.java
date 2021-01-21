package com.mutual.amps.variables.providers;

import java.util.List;

import com.mutual.amps.variables.models.Variable;
import com.mutual.amps.variables.models.repo.IVariableRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariableServiceImpl implements IVariableService {

    @Autowired
    private IVariableRepo variableRepo;

    @Override
    public List<Variable> listarTodo() {
       return this.variableRepo.findAll();
    }

    @Override
    public Variable buscarPorId(Integer id) {
        return this.variableRepo.findById(id).orElse(null);
    }

    @Override
    public Variable buscarPorNombre(String nombre) {
       return null;
    }

    @Override
    public void guardar(Variable variable) {
        this.variableRepo.save(variable);

    }

  /*   @Override
    public void eliminar(Integer id) {
       this.variableRepo.delete(entity);

    } */
    
}
