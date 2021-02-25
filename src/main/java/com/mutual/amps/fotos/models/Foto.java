package com.mutual.amps.fotos.models;

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
@Table( name = "fotos" )
@Data
@NoArgsConstructor
public class Foto implements Serializable{

    

    /**
     *
     */
    private static final long serialVersionUID = -7391245278208442491L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String publicId;

    @NotBlank
    private String url;
    
}
