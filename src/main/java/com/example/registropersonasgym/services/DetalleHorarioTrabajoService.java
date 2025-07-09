package com.example.registropersonasgym.services;

import com.example.registropersonasgym.models.DetalleHorarioTrabajo;
import com.example.registropersonasgym.repositories.DetalleHorarioTrabajoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleHorarioTrabajoService {

    private final DetalleHorarioTrabajoRepository repo;

    public DetalleHorarioTrabajoService(DetalleHorarioTrabajoRepository repo) {
        this.repo = repo;
    }

    public List<DetalleHorarioTrabajo> getAll() {
        return repo.findAll();
    }

    public Optional<DetalleHorarioTrabajo> getById(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    public DetalleHorarioTrabajo create(DetalleHorarioTrabajo d) {
        return repo.save(d);
    }

    @Transactional
    public DetalleHorarioTrabajo update(Integer id, DetalleHorarioTrabajo cambios) {
        return repo.findById(id).map(d -> {
            d.setCliente(cambios.getCliente());
            d.setHorarioTrabajo(cambios.getHorarioTrabajo());
            return repo.save(d);
        }).orElseThrow(() -> new RuntimeException("DetalleHorario no encontrado"));
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
