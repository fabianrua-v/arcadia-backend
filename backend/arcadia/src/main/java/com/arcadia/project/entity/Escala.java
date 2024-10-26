package com.arcadia.project.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "escala")  
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "escala_id")  
    private Long escalaId;  

    @Column(name = "escala_cualitativa", nullable = false)
    private String escalaCualitativa; 

    @Column(name = "escala_numerica", nullable = false)
    private Integer escalaNumerica;  

    
    public Escala() {
    }

    
    public Escala(String escalaCualitativa, Integer escalaNumerica) {
        this.escalaCualitativa = escalaCualitativa;
        this.escalaNumerica = escalaNumerica;
    }


    public boolean esValida() {
        return (escalaCualitativa == null || escalaNumerica == null) && (escalaCualitativa != null || escalaNumerica != null);
    }
}
