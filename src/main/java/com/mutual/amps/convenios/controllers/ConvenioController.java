package com.mutual.amps.convenios.controllers;

import java.util.List;

import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.convenios.providers.IConvenioService;
import com.mutual.amps.fotos.providers.IFotoService;
import com.mutual.amps.usuarios.providers.IUsuarioService;

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
@RequestMapping("convenios")
@CrossOrigin(origins = {"http://localhost:4200", "https://amps-front-test.herokuapp.com" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
public class ConvenioController {


    @Autowired
    private IFotoService fotoService;
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IConvenioService convenioService;

    @GetMapping()
    public ResponseEntity<List<Convenio>> listar() {

        System.out.println("Convenios listados");

        return ResponseEntity.status(HttpStatus.OK).body(convenioService.listarTodo());
    }

    @GetMapping("editar/{id}")
    public ResponseEntity<Convenio> buscarPorId(@PathVariable Integer id) {

        System.out.println("Convenio encontrado");

        return ResponseEntity.status(HttpStatus.OK).body(convenioService.buscarPorId(id));
    }

    @PostMapping("crear")
    public ResponseEntity<Convenio> agregar(@RequestBody Convenio convenio) {
 
        if(convenio.getFoto().getUrl() != "") {

            fotoService.guardarFoto(convenio.getFoto());
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
    public ResponseEntity<Convenio> editar(@RequestBody Convenio convenio) {

        System.out.println(convenio);
        
        if(convenio.getFoto().getUrl() != "") {

            fotoService.guardarFoto(convenio.getFoto());
            convenio.setFoto(convenio.getFoto());
        } else {
            convenio.setFoto(null);
        }
        

        usuarioService.guardar(convenio.getUsuario());

        convenio.setUsuario(convenio.getUsuario());

        /* socio.setFoto(this.url); */

        convenioService.guardar(convenio);

        /* this.url = null; */

        return ResponseEntity.status(HttpStatus.OK).body(convenio);

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Convenio> eliminar(@PathVariable Integer id) {
        this.convenioService.eliminar(id);

        Convenio convenio = new Convenio();

        return ResponseEntity.status(HttpStatus.OK).body(convenio);
    }
    
}
