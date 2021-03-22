package com.mutual.amps.socios.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.mutual.amps.fotos.providers.IFotoService;
import com.mutual.amps.socios.models.EstadoCivil;
import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.models.Tipo;
import com.mutual.amps.socios.models.TipoDocumento;
import com.mutual.amps.socios.providers.ISocioService;
import com.mutual.amps.descuentos.models.Descuento;
import com.mutual.amps.descuentos.providers.IDescuentoService;
import com.mutual.amps.usuarios.providers.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
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
@RequestMapping("socios")
public class SocioController {

    @Autowired
    private IFotoService fotoService;

    @Autowired
    private ISocioService socioService;

    @Autowired
    private IUsuarioService usuarioService;

    
    @Autowired
    private IDescuentoService descuentosService;

    @GetMapping()
    public ResponseEntity<List<Socio>> listar() {

        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.listarTodo());
    }

    @GetMapping("todos-no-baja")
    public ResponseEntity<List<Socio>> listarTodosNoBaja() {

        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.listarTodosNoBaja());
    }

    @GetMapping("tipos_docs")
    public ResponseEntity<List<TipoDocumento>> listarTipoDocs() {

        System.out.println("Tipos de documentos listados");

        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.listarTiposDocs());
    }

    @GetMapping("buscar/{param}")
    public ResponseEntity<List<Socio>> buscar(@PathVariable String param) {
        System.out.println(param);
        
        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.buscar(param));
    }
    @GetMapping("buscarPorDoc/{param}")
    public ResponseEntity<Socio> buscarPorDoc(@PathVariable String param) {
        System.out.println(param);
        
        Long numero = Long.parseLong(param);

        System.out.println(numero);
        
        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.buscarPorDoc(numero));
    }
    
    @GetMapping("estados_civiles")
    public ResponseEntity<List<EstadoCivil>> listarEstadosCiviles() {
        
        System.out.println("Estados civiles listados");
        
        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.listarEstadosCiviles());
    }
    
    @GetMapping("tipos")
    public ResponseEntity<List<Tipo>> listarTipos() {
        
        System.out.println("Tipos listados");
        
        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.listarTipos());
    }
    
    @PostMapping("crear")
    public ResponseEntity<?> agregar(@Valid @RequestBody Socio socio, BindingResult result) {
        
        if(result.hasErrors()){
            
            List<String> errors = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        }

        if(socio.getFoto().getUrl() != null && socio.getFoto().getUrl().length() > 0) {
            
            this.fotoService.guardarFoto(socio.getFoto());
            socio.setFoto(socio.getFoto());
        } else {
            socio.setFoto(null);
        }
        
        
        this.usuarioService.guardar(socio.getUsuario());
        socio.setUsuario(socio.getUsuario());
        
        this.socioService.guardar(socio);

        if(!socio.getBaja()){
            this.descuentosService.crearDescuentos(socio);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(socio);
            
    

    
        
    }
    
    @PutMapping("editar")
    public ResponseEntity<?> editar(@Valid @RequestBody Socio socio, BindingResult result) {
        

        if(result.hasErrors()){
            
            List<String> errors = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        }
        
        if(socio.getFoto().getUrl() != null && socio.getFoto().getUrl().length() > 0) {
            
            this.fotoService.guardarFoto(socio.getFoto());
            socio.setFoto(socio.getFoto());
        } else {
            socio.setFoto(null);
        }
        
        
        socio.setCuotaSocial(socio.getCuotaSocial());
        
        this.usuarioService.guardar(socio.getUsuario());
        
        socio.setUsuario(socio.getUsuario());
        
        this.socioService.guardar(socio);

        if(!socio.getBaja()){
            this.descuentosService.crearDescuentos(socio);
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(socio);
        
    }
    
    
    
    @GetMapping("/editar/{id}")
    public ResponseEntity<Socio> buscarPorId(@PathVariable Integer id) {
      /*   Socio socioBuscado = new Socio(); */
        
        Socio socioBuscado = this.socioService.buscarPorId(id);
        
            
        return ResponseEntity.status(HttpStatus.OK).body(socioBuscado);
            
    }
        
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        
        Socio socio = this.socioService.buscarPorId(id);
        
        
        List<Descuento> descuentos = this.descuentosService.listarPorSocio(socio);
        
        if(descuentos.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(descuentos);
        } else {
            
            this.socioService.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(id);

        }

    }

    @DeleteMapping("/confirmar-eliminar/{id}")
    public ResponseEntity<?> confirmarEliminar(@PathVariable Integer id) {
        
        Socio socio = this.socioService.buscarPorId(id);
    
        if(socio != null){

            this.socioService.eliminar(id);

            return ResponseEntity.status(HttpStatus.OK).body(id);
        } else {
            
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        

    }

    @GetMapping("contar")
    public ResponseEntity<Integer> contar() {
        
        Integer total = this.socioService.contarSocios();
        if(total != null){

            return ResponseEntity.status(HttpStatus.OK).body(total);
        } else {
            
            return ResponseEntity.status(HttpStatus.OK).body(0);
        }
    }

    @GetMapping("exportar")
    public ResponseEntity<InputStreamResource> exportar() throws IOException {

        ByteArrayInputStream stream = this.socioService.exportarTodos();


        return ResponseEntity.ok()
                .body(new InputStreamResource(stream));

    }
        
}
    
    