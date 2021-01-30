package com.mutual.amps.descuentos.controllers;


import java.util.List;

import com.mutual.amps.descuentos.models.Descuento;

import com.mutual.amps.descuentos.providers.IDescuentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("descuentos")
public class DescuentoController {

    @Autowired
    private IDescuentoService descuentoService;

    @GetMapping()
    public ResponseEntity<List<Descuento>> listar(){

        return ResponseEntity.status(HttpStatus.OK).body(this.descuentoService.listarTodo());
    }

    @GetMapping("liquidar/{mes}")
    public ResponseEntity<List<Descuento>> listarDescuentoDelMes(@PathVariable String mes){

        System.out.println(mes);

        return ResponseEntity.status(HttpStatus.OK).body(this.descuentoService.getDescuentosByFecha(mes));
    }

    @GetMapping("editar/{id}")
    public ResponseEntity<Descuento> buscarPorId(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(this.descuentoService.buscarPorId(id));
    }
    
    @PostMapping("crear")
    public ResponseEntity<Descuento> agregar(@RequestBody Descuento descuento){
        
        this.descuentoService.guardar(descuento);
        
        this.descuentoService.guardarItems(descuento);
        
        return ResponseEntity.status(HttpStatus.OK).body(descuento);
    }


    @PutMapping("editar")
    public ResponseEntity<Descuento> editar(@RequestBody Descuento descuento) {
        
        
        this.descuentoService.guardar(descuento);

        this.descuentoService.guardarItems(descuento);
        
        return ResponseEntity.status(HttpStatus.OK).body(descuento);
        
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Descuento> eliminar(@PathVariable Long id) {
        this.descuentoService.eliminar(id);
        
        Descuento descuento = new Descuento();
        
        return ResponseEntity.status(HttpStatus.OK).body(descuento);
    }

    @GetMapping("total")
    public ResponseEntity<Double> totalRecuadado() {
        
        return ResponseEntity.status(HttpStatus.OK).body(this.descuentoService.sumarTotalRecaudado());
    }






    
}
