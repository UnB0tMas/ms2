package com.example.registropersonasgym.repositories;

import com.example.registropersonasgym.models.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembresiaRepository extends JpaRepository<Membresia, Integer> {
    List<Membresia> findByNombreContainingIgnoreCase(String nombre);
}
