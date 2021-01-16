package com.mutual.amps.usuarios.providers;

import java.util.List;

import com.mutual.amps.usuarios.models.Rol;
import com.mutual.amps.usuarios.models.repo.IRolRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements IRolService {


    @Autowired
    private IRolRepo rolRepo;

    @Override
    public List<Rol> listarTodo() {
        return this.rolRepo.findAll();
    }

    @Override
    public Rol buscarPorId(Integer id) {
        return this.rolRepo.findById(id).orElse(null);
    }

    /* @Override
    public void guardar(Rol rol) {
        this.rolRepo.save(rol);

    }

    @Override
    public void eliminar(Integer id) {
        this.rolRepo.deleteById(id);

    } */
    
}
