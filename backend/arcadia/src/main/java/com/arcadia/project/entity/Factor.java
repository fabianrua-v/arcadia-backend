package com.arcadia.project.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "factor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factor_id")
    private Integer factorId;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private boolean estado;

    @Column(name = "fecha_modificacion", nullable = false)
    private LocalDateTime fechaModificacion;

    @Column(name = "modelo_id", nullable = false)
    private Integer modeloId;

    // Relación uno a muchos con Caracteristica, permitiendo lista nula
    @OneToMany(mappedBy = "factor")
    private List<Caracteristica> caracteristicas;
}


