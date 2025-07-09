package com.example.registropersonasgym.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_horario_trabajo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleHorarioTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detalleHorarioId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "horario_id", nullable = false)
    private HorarioTrabajo horarioTrabajo;

    @Column(nullable = false)
    private Boolean estado = true;
}
