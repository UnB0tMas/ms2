package com.example.registropersonasgym.repositories;

import com.example.registropersonasgym.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> { }
