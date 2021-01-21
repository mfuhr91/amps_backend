package com.mutual.amps.variables.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table( name = "variables")
@Data
@NoArgsConstructor
public class Variable implements Serializable {

    private static final long serialVersionUID = -5153840497161299971L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private Double valor;
}
