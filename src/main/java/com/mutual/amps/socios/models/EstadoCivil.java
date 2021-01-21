package com.mutual.amps.socios.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "estados_civiles" )
@Data
@NoArgsConstructor
public class EstadoCivil implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 6980045323023070102L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    
}
