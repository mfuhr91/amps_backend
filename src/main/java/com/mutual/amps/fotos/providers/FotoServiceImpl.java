package com.mutual.amps.fotos.providers;

import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.convenios.providers.IConvenioService;
import com.mutual.amps.fotos.models.Foto;
import com.mutual.amps.fotos.models.repo.IFotoRepo;
import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.providers.ISocioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FotoServiceImpl implements IFotoService {

    @Autowired
    private IFotoRepo fotoRepo;
    
    @Autowired
    private IConvenioService convenioService;

    @Autowired
    private ISocioService socioService;
    
    @Override
    public void guardarFoto(Foto foto) {
        this.fotoRepo.save(foto);

    }

    @Override
    public void eliminarFoto(Integer id) {
        this.fotoRepo.deleteById(id);

    }

    @Override
    public Foto buscarPorId(Integer id) {
       return this.fotoRepo.findById(id).orElse(null);
    }

    @Override
    public Foto buscarPorPublicId(String publicId) {
        return this.fotoRepo.findByPublicId(publicId);
    }

    @Override
    public Foto eliminarFotoSegunIdYTipo(String tipo, String idString, String publicId) {
       
        Foto foto = this.buscarPorPublicId(publicId);

        Integer id = Integer.parseInt(idString);

        if(foto != null){

            if(tipo.equals("socio")){
                Socio socio = this.socioService.buscarPorId(id);
                socio.setFoto(null);
                this.socioService.guardar(socio);
            } else {
                Convenio convenio = this.convenioService.buscarPorId(id);
                convenio.setFoto(null);
                this.convenioService.guardar(convenio);
            }
            this.eliminarFoto(foto.getId());

            return foto;
        }
        
        return null;
    }
}
