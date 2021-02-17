package com.mutual.amps.usuarios.controllers;


import java.util.List;

import com.mutual.amps.usuarios.models.Rol;
import com.mutual.amps.usuarios.models.Usuario;
import com.mutual.amps.usuarios.providers.IRolService;
import com.mutual.amps.usuarios.providers.IUsuarioService;



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
@RequestMapping("usuarios")
public class UsuarioController {

   

    @Autowired
    private IRolService rolService;

    @Autowired
    private IUsuarioService usuarioService;

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
        

        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.buscarPorId(id));
    }
    
    @PostMapping("crear")
    public ResponseEntity<Usuario> agregar(@RequestBody Usuario usuario){
        
        this.usuarioService.guardar(usuario);
        
       
        
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }


    @PutMapping("editar")
    public ResponseEntity<Usuario> editar(@RequestBody Usuario usuario) {
        
        
        this.usuarioService.guardar(usuario);
        
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
        
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {

        try{
            this.usuarioService.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Usuario());

        } catch(Exception e) {
    
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500_INTERNAL_SERVER_ERROR");
        }
    }


    
}
