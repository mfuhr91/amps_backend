package com.mutual.amps.localidades.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name= "localidades")
@Data
@NoArgsConstructor
public class Localidad implements Serializable{


    private static final long serialVersionUID = 5846184284897099440L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String nombre;

    private Integer cp;
    
}
