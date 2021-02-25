package com.mutual.amps.socios.models;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mutual.amps.descuentos.models.Descuento;
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


    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    @NotNull
    private Long cuil;

    @NotBlank
    private String direccion;

    @NotNull
    private Boolean extranjero;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(timezone = "GMT-03:00")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaIngresoLaboral;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(timezone = "GMT-03:00")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variable_id")
    private Variable cuotaSocial;

    private Double seguroVida;

    private Double cuotaDeporte;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foto_id")
    private Foto foto;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(timezone = "GMT-03:00")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaAlta;

    @Temporal(TemporalType.DATE)
    @JsonFormat(timezone = "GMT-03:00")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaBaja;

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_civil_id")
    private EstadoCivil estadoCivil;


    @NotNull
    private Integer legajo;

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

    @NotBlank
    private String correo;

    @Column(columnDefinition="text")
    private String motivoBaja;

    @NotNull
    private Long numCuenta;

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_documento_id")
    private TipoDocumento tipoDocumento;
    
    @NotNull
    private Long numDoc;

    @NotNull
    private Boolean baja;

    @NotNull
    private Boolean habilitado;

    @NotNull
    private Long telefono;

  
    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Descuento> descuento;

   
}
