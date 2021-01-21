package com.mutual.amps.localidades.controllers;

import java.util.List;

import com.mutual.amps.localidades.models.Localidad;
import com.mutual.amps.localidades.providers.ILocalidadService;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(   origins = "*", 
                methods= {RequestMethod.GET})
@RequestMapping("/localidades")
public class LocalidadController {

    @Autowired
    private ILocalidadService localidadService;


    @GetMapping()
    public ResponseEntity<List<Localidad>> listar() {

        System.out.println("Localidades listadas");

        return ResponseEntity.status(HttpStatus.OK).body(this.localidadService.listarTodo());
    }
    
    
}
