package com.mutual.amps.categorias.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.mutual.amps.categorias.models.Categoria;
import com.mutual.amps.categorias.providers.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorias")
public class CategoriaController {
    

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<List<Categoria>> listarCategorias() {

        return ResponseEntity.status(HttpStatus.OK).body(this.categoriaService.listarTodo());
    }

    @PostMapping("crear")
    public ResponseEntity<?> agregar(@Valid @RequestBody Categoria categoria, BindingResult result) {

        if(result.hasErrors()){
            
            List<String> errors = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        }

        this.categoriaService.guardar(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);

    }

    @PutMapping("editar")
    public ResponseEntity<?> editar(@Valid @RequestBody Categoria categoria, BindingResult result) {

        if(result.hasErrors()){
            
            List<String> errors = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        }        

        this.categoriaService.guardar(categoria);

        return ResponseEntity.status(HttpStatus.OK).body(categoria);

    }

    @GetMapping("buscar/{param}")
    public ResponseEntity<List<Categoria>> buscar(@PathVariable String param) {
        
        return ResponseEntity.status(HttpStatus.OK).body(this.categoriaService.buscar(param));
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Categoria> eliminar(@PathVariable Integer id) {
        this.categoriaService.eliminar(id);

        Categoria categoria = new Categoria();

        return ResponseEntity.status(HttpStatus.OK).body(categoria);
    }


}
