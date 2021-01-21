package com.mutual.amps.fotos.providers;

import com.mutual.amps.fotos.models.Foto;

public interface IFotoService {

    public void guardarFoto(Foto foto);

    public void eliminarFoto(Integer id);
    
}
