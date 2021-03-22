package com.mutual.amps.convenios.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.mutual.amps.categorias.models.Categoria;
import com.mutual.amps.categorias.providers.ICategoriaService;
import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.convenios.providers.IConvenioService;
import com.mutual.amps.fotos.models.Foto;
import com.mutual.amps.fotos.providers.IFotoService;
import com.mutual.amps.usuarios.models.Usuario;
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
@RequestMapping("convenios")
public class ConvenioController {


    @Autowired
    private IFotoService fotoService;
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IConvenioService convenioService;

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<List<Convenio>> listar() {

        System.out.println("Convenios listados");

        return ResponseEntity.status(HttpStatus.OK).body(convenioService.listarTodo());
    }

    @GetMapping("todos-no-baja")
    public ResponseEntity<List<Convenio>> listarTodosNoBaja() {

        return ResponseEntity.status(HttpStatus.OK).body(this.convenioService.listarTodosNoBaja());
    }

    @GetMapping("editar/{id}")
    public ResponseEntity<Convenio> buscarPorId(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(convenioService.buscarPorId(id));

    }

    @PostMapping("crear")
    public ResponseEntity<?> agregar(@Valid @RequestBody Convenio convenio, BindingResult result) {
 
        if(result.hasErrors()){
            
            List<String> errors = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        }

        if(convenio.getFoto().getUrl() != null && convenio.getFoto().getUrl().length() > 0 ) {
            
            this.fotoService.guardarFoto(convenio.getFoto());
            convenio.setFoto(convenio.getFoto());
        } else {
        
            convenio.setFoto(null);
        }

        
        usuarioService.guardar(convenio.getUsuario());
        convenio.setUsuario(convenio.getUsuario());

        convenioService.guardar(convenio);
        


        return ResponseEntity.status(HttpStatus.CREATED).body(convenio);

    }

    @PutMapping("editar")
    public ResponseEntity<?> editar(@Valid @RequestBody Convenio convenio, BindingResult result) {

        if(result.hasErrors()){
            
            List<String> errors = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        }
    

        if(convenio.getFoto().getUrl() != null && convenio.getFoto().getUrl().length() > 0 ) {
            
            this.fotoService.guardarFoto(convenio.getFoto());
            convenio.setFoto(convenio.getFoto());
        } else {
        
            convenio.setFoto(null);
        }

        if(convenio.getId() == 1){

            convenio.setUsuario(null);
        } else {

            usuarioService.guardar(convenio.getUsuario());
    
            convenio.setUsuario(convenio.getUsuario());

        }
        


        convenioService.guardar(convenio);

        return ResponseEntity.status(HttpStatus.OK).body(convenio);

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Convenio> eliminar(@PathVariable Integer id) {

        if(id == 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {

            this.convenioService.eliminar(id);

            return ResponseEntity.status(HttpStatus.OK).body(null);

        }


        
    }

    @GetMapping("buscar/{param}")
    public ResponseEntity<List<Convenio>> buscar(@PathVariable String param) {

        
        
        return ResponseEntity.status(HttpStatus.OK).body(this.convenioService.buscar(param));
    }

    @GetMapping("buscarPorUsuario/{nombreUsuario}")
    public ResponseEntity<Convenio> buscarPorUsuario(@PathVariable String nombreUsuario) {

        Usuario usuario = this.usuarioService.buscarPorNombreUsuario(nombreUsuario).get(0);
        
        return ResponseEntity.status(HttpStatus.OK).body(this.convenioService.buscarPorUsuario(usuario));
    }

    @GetMapping("contar")
    public ResponseEntity<Integer> contar() {

        Integer total = this.convenioService.contarConvenios();
        if(total != null){

            return ResponseEntity.status(HttpStatus.OK).body(total);
        } else {
            
            return ResponseEntity.status(HttpStatus.OK).body(0);
        }
        
    }

    @GetMapping("categoria/{nombreCategoria}")
    public ResponseEntity<List<Convenio>> buscarPorCategoria(@PathVariable String nombreCategoria) {

        System.out.println(nombreCategoria);
        
        Categoria cat = this.categoriaService.buscarPorNombre(nombreCategoria);

                
        return ResponseEntity.status(HttpStatus.OK).body(this.convenioService.buscarPorCategoria(cat));
    }
   
    
}
