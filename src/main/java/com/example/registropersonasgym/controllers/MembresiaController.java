package com.example.registropersonasgym.controllers;

import com.example.registropersonasgym.models.Membresia;
import com.example.registropersonasgym.services.MembresiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membresias")
@CrossOrigin // Si lo necesitas para Angular
public class MembresiaController {

    private final MembresiaService service;

    public MembresiaController(MembresiaService service) {
        this.service = service;
    }

    // LISTAR TODAS LAS MEMBRESÍAS (ideal para un select en Angular)
    @GetMapping
    public List<Membresia> list() {
        return service.getAll();
    }

    // OBTENER UNA MEMBRESÍA POR ID (opcional)
    @GetMapping("/{id}")
    public ResponseEntity<Membresia> get(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREAR MEMBRESÍA
    @PostMapping
    public ResponseEntity<Membresia> create(@RequestBody Membresia membresia) {
        Membresia created = service.create(membresia);
        return ResponseEntity.ok(created);
    }

    // ACTUALIZAR MEMBRESÍA
    @PutMapping("/{id}")
    public ResponseEntity<Membresia> update(@PathVariable Integer id, @RequestBody Membresia cambios) {
        try {
            Membresia updated = service.update(id, cambios);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // ELIMINAR MEMBRESÍA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
