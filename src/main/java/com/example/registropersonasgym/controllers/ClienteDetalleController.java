package com.example.registropersonasgym.controllers;

import com.example.registropersonasgym.models.ClienteDetalle;
import com.example.registropersonasgym.services.ClienteDetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes-detalle")
public class ClienteDetalleController {

    private final ClienteDetalleService service;

    public ClienteDetalleController(ClienteDetalleService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteDetalle> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDetalle> get(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteDetalle> create(@RequestBody ClienteDetalle detalle) {
        ClienteDetalle created = service.create(detalle);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDetalle> update(@PathVariable Integer id, @RequestBody ClienteDetalle cambios) {
        try {
            ClienteDetalle updated = service.update(id, cambios);
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
