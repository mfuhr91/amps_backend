package com.mutual.amps.usuarios.providers;

import java.util.List;

import com.mutual.amps.usuarios.models.Usuario;

import org.springframework.transaction.annotation.Transactional;

public interface IUsuarioService {

    @Transactional(readOnly = true)
    public List<Usuario> listarTodo();
    
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Integer id);

    public void guardar(Usuario usuario);

    public void eliminar(Integer id);

    @Transactional(readOnly = true)
    public List<Usuario> buscar(String param);
    
}
