package com.mutual.amps.usuarios.controllers;

import java.util.List;

import com.mutual.amps.usuarios.models.Rol;
import com.mutual.amps.usuarios.providers.IRolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

   

    @Autowired
    private IRolService rolService;

    @GetMapping("roles")
    public ResponseEntity<List<Rol>> listarRoles() {

        System.out.println("Roles listados");

        return ResponseEntity.status(HttpStatus.OK).body(rolService.listarTodo());
    }
    @GetMapping("roles/{id}")
    public ResponseEntity<Rol> buscarRol(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.rolService.buscarPorId(id));
    }
    
}
