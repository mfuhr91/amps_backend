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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cuotas")
@Data
@NoArgsConstructor
public class Cuota implements Serializable {

    private static final long serialVersionUID = 1031575162426244207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numCuota;

    
    private Double montoCuota;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy UTC-3")
    private Date fechaCuota;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "descuento_id")
    @JsonIgnore
    private Descuento descuento;
    

    public Cuota(Double montoCuota, Integer numCuota, Date fechaCuota) {
        this.montoCuota = montoCuota;
        this.numCuota = numCuota;
        this.fechaCuota = fechaCuota;
    }
    
}