package com.example.registropersonasgym.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @Column(nullable = false)
    private Double pesoInicio;

    @Column(length = 255)
    private String imgInicio;

    // Relación con Membresia (puede ser null)
    @ManyToOne(optional = true)
    @JoinColumn(name = "membresia_id", nullable = true)
    private Membresia membresia;

    @Column(nullable = false)
    private Boolean estado = true;
}
