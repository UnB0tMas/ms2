package com.example.registropersonasgym.controllers;

import com.example.registropersonasgym.models.DetalleHorarioTrabajo;
import com.example.registropersonasgym.services.DetalleHorarioTrabajoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-horarios")
public class DetalleHorarioTrabajoController {

    private final DetalleHorarioTrabajoService service;

    public DetalleHorarioTrabajoController(DetalleHorarioTrabajoService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetalleHorarioTrabajo> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleHorarioTrabajo> get(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleHorarioTrabajo> create(@RequestBody DetalleHorarioTrabajo detalle) {
        DetalleHorarioTrabajo created = service.create(detalle);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleHorarioTrabajo> update(@PathVariable Integer id, @RequestBody DetalleHorarioTrabajo cambios) {
        try {
            DetalleHorarioTrabajo updated = service.update(id, cambios);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
