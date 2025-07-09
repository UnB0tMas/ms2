package com.example.registropersonasgym.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer areaId;

    @Column(nullable = false, length = 100)
    private String nombreArea;

    @Column(nullable = false)
    private Boolean estado = true;
}
