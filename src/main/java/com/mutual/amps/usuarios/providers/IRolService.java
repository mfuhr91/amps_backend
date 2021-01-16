package com.mutual.amps.usuarios.providers;

import java.util.List;

import com.mutual.amps.usuarios.models.Rol;

import org.springframework.transaction.annotation.Transactional;

public interface IRolService {

    
    @Transactional(readOnly = true)
    public List<Rol> listarTodo();
    
    @Transactional(readOnly = true)
    public Rol buscarPorId(Integer id);

   /*  public void guardar(Rol rol);

    public void eliminar(Integer id); */
}
