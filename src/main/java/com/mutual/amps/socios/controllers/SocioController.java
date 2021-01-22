package com.mutual.amps.socios.controllers;


import java.util.List;

import com.mutual.amps.fotos.providers.IFotoService;
import com.mutual.amps.socios.models.EstadoCivil;
import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.models.Tipo;
import com.mutual.amps.socios.models.TipoDocumento;
import com.mutual.amps.socios.providers.ISocioService;

import com.mutual.amps.usuarios.providers.IUsuarioService;
import com.mutual.amps.variables.providers.IVariableService;

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

import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/socios")
public class SocioController {

    @Autowired
    private IFotoService fotoService;

    @Autowired
    private ISocioService socioService;

    @Autowired
    private IUsuarioService usuarioService;

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
    public ResponseEntity<Socio> agregar(@RequestBody Socio socio) {
 
        if(socio.getFoto().getUrl() != "") {

            fotoService.guardarFoto(socio.getFoto());
            socio.setFoto(socio.getFoto());
        } else {
            socio.setFoto(null);
        }

        
        usuarioService.guardar(socio.getUsuario());
        socio.setUsuario(socio.getUsuario());

        socioService.guardar(socio);
        


        return ResponseEntity.status(HttpStatus.CREATED).body(socio);

    }

    @PutMapping("editar")
    public ResponseEntity<Socio> editar(@RequestBody Socio socio) {
        System.out.println(socio);

        if(socio.getFoto().getUrl() != "") {

            fotoService.guardarFoto(socio.getFoto());
            socio.setFoto(socio.getFoto());
        } else {
            socio.setFoto(null);
        }


        socio.setCuotaSocial(socio.getCuotaSocial());

        usuarioService.guardar(socio.getUsuario());

        socio.setUsuario(socio.getUsuario());

        socioService.guardar(socio);

        return ResponseEntity.status(HttpStatus.OK).body(socio);

    }

    

    @GetMapping("/editar/{id}")
    public ResponseEntity<Socio> buscarPorId(@PathVariable Integer id) {
        Socio socioBuscado = new Socio();

        socioBuscado = socioService.buscarPorId(id);

        /* socioBuscado = socio; */

        /*
         * if(this.url != null) { socioBuscado.setFoto(this.url); } /* else {
         * socioService.guardar(socioBuscado); }
         */

        return ResponseEntity.status(HttpStatus.OK).body(socioBuscado);

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Socio> eliminar(@PathVariable Integer id) {
        this.socioService.eliminar(id);

        Socio socio = new Socio();

        return ResponseEntity.status(HttpStatus.OK).body(socio);
    }

}
