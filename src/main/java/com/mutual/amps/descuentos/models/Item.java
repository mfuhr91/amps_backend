package com.mutual.amps.descuentos.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item implements Serializable{
   
    private static final long serialVersionUID = 7817946807227723936L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     
    private Long id;

    @NotNull
    private Double valor;

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "descuento_id")
    @JsonIgnore
    private Descuento descuento;

    public Item(Double valor){
        this.valor = valor;
    };
}
