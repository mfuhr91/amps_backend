package com.mutual.amps.convenios.providers;

import java.util.List;

import com.mutual.amps.categorias.models.Categoria;
import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.usuarios.models.Usuario;

import org.springframework.transaction.annotation.Transactional;

public interface IConvenioService {

    @Transactional(readOnly = true)
    public List<Convenio> listarTodo();
    
    @Transactional(readOnly = true)
    public Convenio buscarPorId(Integer id);

    public void guardar(Convenio convenio);

    public void eliminar(Integer id);

    @Transactional(readOnly = true)
    public List<Convenio> buscar(String parametro);
    
    @Transactional(readOnly = true)
    public Integer contarConvenios();

    @Transactional(readOnly = true)
    public List<Convenio> buscarPorCategoria(Categoria categoria);


     @Transactional(readOnly = true)
     public Convenio buscarPorUsuario(Usuario usuario);
}
