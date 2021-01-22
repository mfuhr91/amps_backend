package com.mutual.amps.socios.models;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mutual.amps.fotos.models.Foto;
import com.mutual.amps.localidades.models.Localidad;
import com.mutual.amps.usuarios.models.Usuario;
import com.mutual.amps.variables.models.Variable;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "socios")
@Data
@NoArgsConstructor
public class Socio implements Serializable {

    private static final long serialVersionUID = -8033770548789716221L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;


    /* @NotBlank */
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    private Long cuil;

    private String direccion;

    private Boolean extranjero;

    @Temporal(TemporalType.TIMESTAMP)
    /* @DateTimeFormat(pattern = "dd/MM/yyyy") */
    private Date fechaIngresoLaboral;

    @Temporal(TemporalType.TIMESTAMP)
    /* @DateTimeFormat(pattern = "dd/MM/yyyy") */
    private Date fechaNacimiento;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variable_id")
    private Variable cuotaSocial;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foto_id")
    private Foto foto;

    @Temporal(TemporalType.TIMESTAMP)
    /* @DateTimeFormat(pattern = "dd/MM/yyyy") */
    private Date fechaAlta;

    @Temporal(TemporalType.TIMESTAMP)
    /* @DateTimeFormat(pattern = "dd/MM/yyyy") */
    private Date fechaBaja;

    /* @NotBlank */
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_civil_id")
    private EstadoCivil estadoCivil;


    /* @NotBlank */
    private Integer legajo;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

    /* @NotBlank */
    private String correo;

    @Column(columnDefinition="text", name = "motivo_baja")
    private String motivoBaja;

    /* @NotBlank */
    @Column(name = "num_cuenta")
    private Long numCuenta;

    /* @NotBlank */
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_documento_id")
    private TipoDocumento tipoDocumento;
    
    /* @NotBlank */
    @Column(name = "num_doc")
    private Long numDoc;

    private Boolean baja;

    /* @NotBlank */
    private Long telefono;

  
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

   
}
