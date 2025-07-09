package com.example.registropersonasgym.services;

import com.example.registropersonasgym.models.Area;
import com.example.registropersonasgym.repositories.AreaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    private final AreaRepository repo;

    public AreaService(AreaRepository repo) {
        this.repo = repo;
    }

    public List<Area> getAll() {
        return repo.findAll();
    }

    public List<Area> getActivas() {
        return repo.findByEstadoTrue();
    }

    public Optional<Area> getById(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    public Area create(Area a) {
        return repo.save(a);
    }

    @Transactional
    public Area update(Integer id, Area cambios) {
        return repo.findById(id).map(a -> {
            a.setNombreArea(cambios.getNombreArea());
            return repo.save(a);
        }).orElseThrow(() -> new RuntimeException("Área no encontrada"));
    }

    @Transactional
    public void delete(Integer id) {
        repo.findById(id).ifPresent(a -> {
            a.setEstado(false);
            repo.save(a);
        });
    }
}
