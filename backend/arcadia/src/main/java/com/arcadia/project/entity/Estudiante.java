package com.arcadia.project.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estudiante_id")
    private Integer estudiante_id;

    @Column(nullable = false)
    private String cedula;
    
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private  String correoInstitucional;

    @Column(nullable = false)
    private  String correoPersonal;

    @Column(nullable = false)
    private String celular;

}