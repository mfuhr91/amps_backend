package com.mutual.amps.descuentos.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.socios.models.Socio;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "descuentos")
@Data
@NoArgsConstructor
public class Descuento implements Serializable{
    private static final long serialVersionUID = 6153139326616896166L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descripcion;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT-03:00")
    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    private Date fechaAlta;

    
    @Temporal(TemporalType.DATE)
    @JsonFormat(timezone = "GMT-03:00")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ultimaCuota;

    @NotNull
    private Integer numCuotas;

    
    private Double valorCuota;

    @NotNull
    private Double valorSubTotal;


    private Double valorTotal;


    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id")
    private Socio socio;

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "convenio_id")
    private Convenio convenio;

    @OneToMany(mappedBy = "descuento", cascade = CascadeType.ALL)
    private List<Cuota> cuotas;

    @OneToMany(mappedBy = "descuento", cascade = CascadeType.ALL)
    private List<Item> items;

}
