package com.mutual.amps.variables.controllers;

import java.util.List;

import com.mutual.amps.variables.models.Variable;
import com.mutual.amps.variables.providers.IVariableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/variables")
@CrossOrigin(origins = {"http://localhost:4200", "https://amps-front-test.herokuapp.com" }, methods = { RequestMethod.GET, RequestMethod.PUT})
public class VariableController {
    
    @Autowired
    private IVariableService variableService;

    @GetMapping("")
    public ResponseEntity<List<Variable>> listar() {

        return ResponseEntity.status(HttpStatus.OK).body(variableService.listarTodo());
    }

    @GetMapping("editar/{id}")
    public ResponseEntity<Variable> buscarPorId(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(variableService.buscarPorId(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<Variable> buscarVariable(@PathVariable Integer id) {
    
        return ResponseEntity.status(HttpStatus.OK).body(this.variableService.buscarPorId(id));
    }

    /* @PostMapping("crear")
    public ResponseEntity<Variable> agregar(@RequestBody Variable variable) {

        this.variableService.guardar(variable);

        return ResponseEntity.status(HttpStatus.CREATED).body(variable);

    } */

    @PutMapping("editar")
    public ResponseEntity<Variable> editar(@RequestBody Variable variable) {

        this.variableService.guardar(variable);

        return ResponseEntity.status(HttpStatus.OK).body(variable);

    }
    
}
