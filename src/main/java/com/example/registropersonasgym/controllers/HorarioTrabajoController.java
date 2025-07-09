package com.example.registropersonasgym.controllers;

import com.example.registropersonasgym.models.HorarioTrabajo;
import com.example.registropersonasgym.services.HorarioTrabajoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioTrabajoController {

    private final HorarioTrabajoService service;

    public HorarioTrabajoController(HorarioTrabajoService service) {
        this.service = service;
    }

    @GetMapping
    public List<HorarioTrabajo> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioTrabajo> get(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HorarioTrabajo> create(@RequestBody HorarioTrabajo horario) {
        HorarioTrabajo created = service.create(horario);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioTrabajo> update(@PathVariable Integer id, @RequestBody HorarioTrabajo cambios) {
        try {
            HorarioTrabajo updated = service.update(id, cambios);
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
