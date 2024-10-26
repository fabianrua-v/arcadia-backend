package com.arcadia.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "fuente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fuente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuenteId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String soporte;
}
