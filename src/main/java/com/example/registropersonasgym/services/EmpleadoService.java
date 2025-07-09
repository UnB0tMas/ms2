package com.example.registropersonasgym.services;

import com.example.registropersonasgym.models.Empleado;
import com.example.registropersonasgym.models.Persona;
import com.example.registropersonasgym.repositories.EmpleadoRepository;
import com.example.registropersonasgym.repositories.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repo;
    private final PersonaRepository personaRepo;

    public EmpleadoService(EmpleadoRepository repo, PersonaRepository personaRepo) {
        this.repo = repo;
        this.personaRepo = personaRepo;
    }

    public List<Empleado> getAll() {
        return repo.findAll();
    }

    public Optional<Empleado> getById(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    public Empleado create(Empleado e) {
        Persona persona = e.getPersona();
        if (persona.getPersonaId() == null) {
            persona = personaRepo.save(persona);
        } else {
            persona = personaRepo.findById(persona.getPersonaId()).orElseThrow(
                    () -> new RuntimeException("Persona no encontrada")
            );
        }
        e.setPersona(persona);
        return repo.save(e);
    }

    @Transactional
    public Empleado update(Integer id, Empleado cambios) {
        return repo.findById(id).map(e -> {
            e.setPersona(cambios.getPersona());
            e.setArea(cambios.getArea());
            e.setFechaContratacion(cambios.getFechaContratacion());
            e.setSueldo(cambios.getSueldo());
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    @Transactional
    public void delete(Integer id) {
        repo.findById(id).ifPresent(e -> {
            e.setEstado(false);
            repo.save(e);
        });
    }
}
