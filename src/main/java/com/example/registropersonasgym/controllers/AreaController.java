package com.example.registropersonasgym.controllers;

import com.example.registropersonasgym.models.Area;
import com.example.registropersonasgym.services.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    private final AreaService service;

    public AreaController(AreaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Area> list() {
        return service.getAll();
    }

    // NUEVO: Listar solo áreas activas
    @GetMapping("/activas")
    public List<Area> listActivas() {
        return service.getActivas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> get(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Area> create(@RequestBody Area area) {
        Area created = service.create(area);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> update(@PathVariable Integer id, @RequestBody Area cambios) {
        try {
            Area updated = service.update(id, cambios);
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
