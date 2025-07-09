package com.example.registropersonasgym.repositories;

import com.example.registropersonasgym.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Integer> {
    List<Area> findByEstadoTrue();
}
