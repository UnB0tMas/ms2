package com.example.registropersonasgym.repositories;

import com.example.registropersonasgym.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> { }
