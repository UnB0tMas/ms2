package com.example.registropersonasgym.repositories;

import com.example.registropersonasgym.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> { }
