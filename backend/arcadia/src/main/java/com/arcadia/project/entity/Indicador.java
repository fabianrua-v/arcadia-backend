package com.arcadia.project.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "indicador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Indicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indicador_id")
    private Integer indicadorId; 

    @Column(nullable = false)
    private String nombre; 

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaModificacion;

    @ManyToOne 
    @JoinColumn(name = "caracteristica_id", nullable = false) 
    private Caracteristica caracteristica;

    @ManyToOne 
    @JoinColumn(name = "fuente_id", nullable = false) 
    private Fuente fuente;
}

