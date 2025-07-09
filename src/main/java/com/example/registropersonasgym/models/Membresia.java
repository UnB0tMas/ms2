package com.example.registropersonasgym.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "membresia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membresia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer membresiaId;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Integer duracionMeses;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private LocalDate fechaInicioVigencia;

    @Column(nullable = true)
    private LocalDate fechaFinVigencia;

    @Column(nullable = false)
    private Boolean estado = true;
}
