package com.mutual.amps.categorias.providers;

import java.util.List;

import com.mutual.amps.categorias.models.Categoria;

import org.springframework.transaction.annotation.Transactional;

public interface ICategoriaService {
    
    
    @Transactional(readOnly = true)
    public List<Categoria> listarTodo();
    
    @Transactional(readOnly = true)
    public Categoria buscarPorId(Integer id);

    @Transactional(readOnly = true)
    public Categoria buscarPorNombre(String nombre);

    public void guardar(Categoria categoria);

    public void eliminar(Integer id);

    @Transactional(readOnly = true)
    public List<Categoria> buscar(String param);


}
