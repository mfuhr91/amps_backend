package com.mutual.amps.usuarios.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.mutual.amps.usuarios.models.Rol;
import com.mutual.amps.usuarios.models.Usuario;
import com.mutual.amps.usuarios.providers.IRolService;
import com.mutual.amps.usuarios.providers.IUsuarioService;

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
@RequestMapping("usuarios")
public class UsuarioController {

   

    @Autowired
    private IRolService rolService;

    @Autowired
    private IUsuarioService usuarioService;

    //ROLES

    @GetMapping("roles")
    public ResponseEntity<List<Rol>> listarRoles() {

        System.out.println("Roles listados");

        return ResponseEntity.status(HttpStatus.OK).body(rolService.listarTodo());
    }
    @GetMapping("roles/{id}")
    public ResponseEntity<Rol> buscarRol(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.rolService.buscarPorId(id));
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> listar(){

        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.listarTodo());
    }


    // USUARIOS

    @GetMapping("buscar/{param}")
    public ResponseEntity<List<Usuario>> buscar(@PathVariable String param) {
        
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.buscar(param));
    }

    @GetMapping("buscarPorNombreUsuario/{usuario}")
    public ResponseEntity<List<Usuario>> buscarPorNombreUsuario(@PathVariable String usuario) {
        
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.buscarPorNombreUsuario(usuario));
    }

    @GetMapping("editar/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id){

        if(id == 1){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.buscarPorId(id));

        }
        
        
    }
    
    @PostMapping("crear")
    public ResponseEntity<?> agregar(@Valid @RequestBody Usuario usuario, BindingResult result){
        

        if(result.hasErrors()){
            
            List<String> errors = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        }

        
        this.usuarioService.guardar(usuario);
        
       
        
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }


    @PutMapping("editar")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result) {


        if(result.hasErrors()){
            
            List<String> errors = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        }
        if(usuario.getId() == 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            this.usuarioService.guardar(usuario);
            
            return ResponseEntity.status(HttpStatus.OK).body(usuario);

        }

        
        
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {

        if( id == 1){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else{

            try{
                this.usuarioService.eliminar(id);
                return ResponseEntity.status(HttpStatus.OK).body(new Usuario());
    
            } catch(Exception e) {
        
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500_INTERNAL_SERVER_ERROR");
            }

        }

    }


    
}
