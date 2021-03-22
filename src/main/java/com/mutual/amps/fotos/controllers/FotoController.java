package com.mutual.amps.fotos.controllers;

import java.io.IOException;
import java.util.Map;

import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.convenios.providers.IConvenioService;
import com.mutual.amps.fotos.models.Foto;
import com.mutual.amps.fotos.providers.CloudinaryService;
import com.mutual.amps.fotos.providers.IFotoService;
import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.models.repo.ISocioRepo;
import com.mutual.amps.socios.providers.ISocioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("fotos")
public class FotoController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private IFotoService fotoService;

    
    @PostMapping("/subir_foto")
    public ResponseEntity<Foto> subirFoto(@RequestParam(value = "foto") MultipartFile file, @RequestParam(value = "tipo") String tipo ) throws IOException {      

        Foto foto = new Foto();
        if (!file.isEmpty()) {

            
            Map result = cloudinaryService.upload(file, tipo);
            
            foto.setUrl(result.get("secure_url").toString());
            foto.setPublicId(result.get("public_id").toString());

            return ResponseEntity.status(HttpStatus.OK).body(foto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(foto);
    }

    @DeleteMapping("/borrar_foto")
    public ResponseEntity<?> borrarFoto(@RequestParam String tipo, @RequestParam String idString, @RequestParam String org, @RequestParam String folder, @RequestParam String numero) throws IOException {     
        
        
        String publicId = org.concat("/").concat(folder).concat("/").concat(numero); 

        Foto foto = this.fotoService.eliminarFotoSegunIdYTipo(tipo, idString, publicId);

 
        System.out.println(this.cloudinaryService.delete(publicId));

        return ResponseEntity.status(HttpStatus.OK).body(foto);
    }
}
