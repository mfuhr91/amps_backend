package com.mutual.amps.socios.controllers;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mutual.amps.Mensaje;
import com.mutual.amps.localidades.providers.ILocalidadService;
import com.mutual.amps.socios.models.Categoria;
import com.mutual.amps.socios.models.EstadoCivil;
import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.models.TipoDocumento;
import com.mutual.amps.socios.providers.CloudinaryService;
import com.mutual.amps.socios.providers.ISocioService;
import com.mutual.amps.usuarios.models.Usuario;
import com.mutual.amps.usuarios.providers.IUsuarioService;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(   origins = "*", 
                methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/socios")
public class SocioController {

   /*  private String url = null; */

    @Autowired
    private ISocioService socioService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private CloudinaryService cloudinaryService;

    
    @GetMapping()
    public ResponseEntity<List<Socio>> listar() {

        System.out.println("Socios listados");

        return ResponseEntity.status(HttpStatus.OK).body(socioService.listarTodo());
    }

    @GetMapping("tipos_docs")
    public ResponseEntity<List<TipoDocumento>> listarTipoDocs() {

        System.out.println("Tipos de documentos listados");

        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.listarTiposDocs());
    }

    @GetMapping("categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {

        System.out.println("Categorias listados");

        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.listarCategorias());
    }
    @GetMapping("estados_civiles")
    public ResponseEntity<List<EstadoCivil>> listarEstadosCiviles() {

        System.out.println("Estados civiles listados");

        return ResponseEntity.status(HttpStatus.OK).body(this.socioService.listarEstadosCiviles());
    }

    

   
    @PostMapping("crear")
    public ResponseEntity<Socio> agregar(@RequestBody Socio socio) {

        

        System.out.println("############## ===> " + socio);
        

       /*  System.out.println(socio.getUsuario()); */
        

        usuarioService.guardar(socio.getUsuario());
        
        socio.setUsuario(socio.getUsuario());

       /*  socio.setFoto(this.url); */
        
        socioService.guardar(socio);
        
      /*   this.url = null;  */

        return ResponseEntity.status(HttpStatus.CREATED).body(socio);

    }
    @PutMapping("editar")
    public ResponseEntity<Socio> editar(@RequestBody Socio socio) {


        System.out.println(socio.getUsuario());

        usuarioService.guardar(socio.getUsuario());
        
        socio.setUsuario(socio.getUsuario());

      /*   socio.setFoto(this.url); */
        
        socioService.guardar(socio);
        
       /*  this.url = null;  */

        return ResponseEntity.status(HttpStatus.OK).body(socio);

    }

    @PostMapping("/subir_foto")
    public ResponseEntity<Socio> subirFoto(@RequestParam(value = "foto") MultipartFile file) throws IOException {

        Socio socio = new Socio();
        if (!file.isEmpty()) {

            System.out.println(file);
            

            socio.setFoto(cloudinaryService.upload(file).get("secure_url").toString());
            /* System.out.println(" URL: " + url); */
           /*  System.out.println(cloudinaryService.upload(file).get("secure_url").toString());
            System.out.println(socio.getFoto()); */
            
            /* Mensaje msj = new Mensaje(url.toString()); */
            
            return ResponseEntity.status(HttpStatus.OK).body(socio);
        }

        System.out.println();
        

        return ResponseEntity.status(HttpStatus.OK).body(socio);
    }

   /*  @CrossOrigin(   origins = "*", 
                methods= {RequestMethod.GET, RequestMethod.POST}) */
    @GetMapping("/editar/{id}")
    public ResponseEntity<Socio> editar(@PathVariable Integer id) {
        Socio socioBuscado = new Socio();

        socioBuscado = socioService.buscarPorId(id);

        /* socioBuscado = socio; */

        /* if(this.url != null) {
           socioBuscado.setFoto(this.url);
        } /* else {
            socioService.guardar(socioBuscado);
        } */ 

        return ResponseEntity.status(HttpStatus.OK).body(socioBuscado);

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {

        socioService.eliminar(id);

        return ResponseEntity.status(HttpStatus.OK).body("Socio eliminado!");
    }

}
