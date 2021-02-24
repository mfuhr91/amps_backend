package com.mutual.amps.socios.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "tipos")
@Data
@NoArgsConstructor
public class Tipo implements Serializable{

    
    private static final long serialVersionUID = 5767056502263097241L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nombre;
    
}
