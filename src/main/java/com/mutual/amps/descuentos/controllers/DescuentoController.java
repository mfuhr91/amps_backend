package com.mutual.amps.descuentos.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutual.amps.descuentos.models.Descuento;

import com.mutual.amps.descuentos.providers.IDescuentoService;
import com.mutual.amps.descuentos.providers.IExportarDescuentos;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("descuentos")
public class DescuentoController {

    @Autowired
    private IDescuentoService descuentoService;

    @Autowired
    private IExportarDescuentos exportarDescuentos;

    @GetMapping()
    public ResponseEntity<List<Descuento>> listar() {

        return ResponseEntity.status(HttpStatus.OK).body(this.descuentoService.listarTodo());
    }

    @GetMapping("liquidar/{mes}")
    public ResponseEntity<List<Descuento>> listarDescuentoDelMes(@PathVariable String mes) {

        System.out.println(mes);

        return ResponseEntity.status(HttpStatus.OK).body(this.descuentoService.getDescuentosByFecha(mes));
    }

    @GetMapping("editar/{id}")
    public ResponseEntity<Descuento> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.descuentoService.buscarPorId(id));
    }

    @PostMapping("crear")
    public ResponseEntity<Descuento> agregar(@RequestBody Descuento descuento) {

        this.descuentoService.guardar(descuento);

        this.descuentoService.guardarItems(descuento);

        return ResponseEntity.status(HttpStatus.OK).body(descuento);
    }

    @PutMapping("editar")
    public ResponseEntity<Descuento> editar(@RequestBody Descuento descuento) {

        this.descuentoService.guardar(descuento);

        this.descuentoService.guardarItems(descuento);

        return ResponseEntity.status(HttpStatus.OK).body(descuento);

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Descuento> eliminar(@PathVariable Long id) {
        this.descuentoService.eliminar(id);

        Descuento descuento = new Descuento();

        return ResponseEntity.status(HttpStatus.OK).body(descuento);
    }

    @GetMapping("total")
    public ResponseEntity<Double> totalRecuadado() {

        return ResponseEntity.status(HttpStatus.OK).body(this.descuentoService.sumarTotalRecaudado());
    }

    @GetMapping("descargar/{mes}")
    public ResponseEntity<ByteArrayResource> descargarArchivo(@PathVariable String mes) throws IOException {
       
       
        
        // Create text file
        File archivo = exportarDescuentos.exportar(mes);

        System.out.println("creando archivo!");
       
        // Download file with ByteArrayResource
        byte[] exportedFileData = Files.readAllBytes(archivo.toPath());
        ByteArrayResource byteArrayResource = new ByteArrayResource(exportedFileData);
            
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + archivo)
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(exportedFileData.length)
                .body(byteArrayResource);
                
    }






    
}
