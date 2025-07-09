package com.example.registropersonasgym.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "horario_trabajo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer horarioId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private LocalDateTime fechaFin;

    @Column(length = 255)
    private String tema;

    @Column(nullable = false)
    private Boolean estado = true;
}
