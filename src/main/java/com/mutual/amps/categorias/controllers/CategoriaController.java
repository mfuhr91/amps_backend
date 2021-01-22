package com.mutual.amps.categorias.controllers;

import java.util.List;

import com.mutual.amps.categorias.models.Categoria;
import com.mutual.amps.categorias.providers.ICategoriaService;
import com.mutual.amps.socios.models.Tipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://amps-front-test.herokuapp.com" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
@RequestMapping("categorias")
public class CategoriaController {
    

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<List<Categoria>> listarCategorias() {

        return ResponseEntity.status(HttpStatus.OK).body(this.categoriaService.listarTodo());
    }
}
