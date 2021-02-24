package com.mutual.amps.descuentos.models;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @NotNull
    private Integer numCuota;

    @NotNull
    private Double valor;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(timezone = "GMT-03:00")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecha;

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "descuento_id")
    @JsonIgnore
    private Descuento descuento;
    

    public Cuota(Double valor, Integer numCuota, Date fecha) {
        this.valor = valor;
        this.numCuota = numCuota;
        this.fecha = fecha;
    }
    
}
