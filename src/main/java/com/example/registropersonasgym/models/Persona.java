package com.example.registropersonasgym.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personaId;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidoP;

    @Column(nullable = false, length = 100)
    private String apellidoM;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 50)
    private String docIdentidad;

    @Column(nullable = false, length = 50)
    private String ndeDocumento;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false)
    private Boolean estado = true;
}
