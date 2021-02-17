package com.mutual.amps.usuarios.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data

public class Usuario implements Serializable{

    private static final long serialVersionUID = -5890266985280630698L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreUsuario;

    private Boolean baja;


    private String contrasena;


    @Temporal(TemporalType.DATE)
    @JsonFormat(timezone = "GMT-03:00")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaAlta;

    @Temporal(TemporalType.DATE)
    @JsonFormat(timezone = "GMT-03:00")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaBaja;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id")
    private Rol rol;


    public Usuario(){
        this.fechaAlta = new Date();
        this.baja = false;
    }
    
}
