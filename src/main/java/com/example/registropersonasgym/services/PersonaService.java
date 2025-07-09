package com.example.registropersonasgym.services;

import com.example.registropersonasgym.models.Persona;
import com.example.registropersonasgym.repositories.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository repo;

    public PersonaService(PersonaRepository repo) {
        this.repo = repo;
    }

    public List<Persona> getAll() {
        return repo.findAll();
    }

    public Optional<Persona> getById(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    public Persona create(Persona p) {
        // estado por defecto = true
        return repo.save(p);
    }

    @Transactional
    public Persona update(Integer id, Persona cambios) {
        return repo.findById(id).map(p -> {
            p.setNombre(cambios.getNombre());
            p.setApellidoP(cambios.getApellidoP());
            p.setApellidoM(cambios.getApellidoM());
            p.setFechaNacimiento(cambios.getFechaNacimiento());
            p.setDocIdentidad(cambios.getDocIdentidad());
            p.setNdeDocumento(cambios.getNdeDocumento());
            p.setTelefono(cambios.getTelefono());
            // no tocamos estado aquí
            return repo.save(p);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }

    @Transactional
    public void delete(Integer id) {
        repo.findById(id).ifPresent(p -> {
            p.setEstado(false);
            repo.save(p);
        });
    }
}
