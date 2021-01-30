package com.mutual.amps.categorias.providers;

import java.util.List;

import com.mutual.amps.categorias.models.Categoria;

import org.springframework.transaction.annotation.Transactional;

public interface ICategoriaService {
    
    
    @Transactional(readOnly = true)
    public List<Categoria> listarTodo();
    
    @Transactional(readOnly = true)
    public Categoria buscarPorId(Integer id);

    public void guardar(Categoria categoria);

    public void eliminar(Integer id);
}
