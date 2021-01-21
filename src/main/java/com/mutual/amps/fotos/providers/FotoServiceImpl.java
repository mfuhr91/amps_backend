package com.mutual.amps.fotos.providers;

import com.mutual.amps.fotos.models.Foto;
import com.mutual.amps.fotos.models.repo.IFotoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FotoServiceImpl implements IFotoService {

    @Autowired
    private IFotoRepo fotoRepo;
    
    @Override
    public void guardarFoto(Foto foto) {
        this.fotoRepo.save(foto);

    }

    @Override
    public void eliminarFoto(Integer id) {
        this.fotoRepo.deleteById(id);

    }
}
