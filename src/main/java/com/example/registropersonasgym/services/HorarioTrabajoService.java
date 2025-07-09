package com.example.registropersonasgym.services;

import com.example.registropersonasgym.models.HorarioTrabajo;
import com.example.registropersonasgym.repositories.HorarioTrabajoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioTrabajoService {

    private final HorarioTrabajoRepository repo;

    public HorarioTrabajoService(HorarioTrabajoRepository repo) {
        this.repo = repo;
    }

    public List<HorarioTrabajo> getAll() {
        return repo.findAll();
    }

    public Optional<HorarioTrabajo> getById(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    public HorarioTrabajo create(HorarioTrabajo h) {
        return repo.save(h);
    }

    @Transactional
    public HorarioTrabajo update(Integer id, HorarioTrabajo cambios) {
        return repo.findById(id).map(h -> {
            h.setEmpleado(cambios.getEmpleado());
            h.setFechaInicio(cambios.getFechaInicio());
            h.setFechaFin(cambios.getFechaFin());
            h.setTema(cambios.getTema());
            return repo.save(h);
        }).orElseThrow(() -> new RuntimeException("Horario no encontrado"));
    }

    @Transactional
    public void delete(Integer id) {
        repo.findById(id).ifPresent(h -> {
            h.setEstado(false);
            repo.save(h);
        });
    }
}
