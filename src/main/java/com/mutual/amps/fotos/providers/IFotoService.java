package com.mutual.amps.fotos.providers;

import com.mutual.amps.fotos.models.Foto;

public interface IFotoService {

    public void guardarFoto(Foto foto);

    public void eliminarFoto(Integer id);

    public Foto buscarPorId(Integer id);

    public Foto buscarPorPublicId(String publicId);

    public Foto eliminarFotoSegunIdYTipo(String tipo, String id, String publicId);
    
}
