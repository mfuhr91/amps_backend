package com.mutual.amps.socios.providers;

import java.util.List;

import com.mutual.amps.socios.models.EstadoCivil;
import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.models.Tipo;
import com.mutual.amps.socios.models.TipoDocumento;

import org.springframework.transaction.annotation.Transactional;

public interface ISocioService {

    @Transactional(readOnly = true)
    public List<Socio> listarTodo();
    
    @Transactional(readOnly = true)
    public Socio buscarPorId(Integer id);

    public void guardar(Socio socio);

    public void eliminar(Integer id);



    @Transactional(readOnly = true)
    public List<TipoDocumento> listarTiposDocs();

    @Transactional(readOnly = true)
    public List<EstadoCivil> listarEstadosCiviles();


    @Transactional(readOnly = true)
    public List<Tipo> listarTipos();

    @Transactional(readOnly = true)
    public List<Socio> buscar(String parametro);
    
    @Transactional(readOnly = true)
    public Socio buscarPorDoc(Long doc);

    @Transactional(readOnly = true)
    public Integer contarSocios();


    
    
}
