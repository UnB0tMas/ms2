package com.example.registropersonasgym.services;

import com.example.registropersonasgym.models.ClienteDetalle;
import com.example.registropersonasgym.repositories.ClienteDetalleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteDetalleService {

    private final ClienteDetalleRepository repo;

    public ClienteDetalleService(ClienteDetalleRepository repo) {
        this.repo = repo;
    }

    public List<ClienteDetalle> getAll() {
        return repo.findAll();
    }

    public Optional<ClienteDetalle> getById(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    public ClienteDetalle create(ClienteDetalle d) {
        return repo.save(d);
    }

    @Transactional
    public ClienteDetalle update(Integer id, ClienteDetalle cambios) {
        return repo.findById(id).map(d -> {
            d.setFechaActualizacion(cambios.getFechaActualizacion());
            d.setPeso(cambios.getPeso());
            d.setImg(cambios.getImg());
            return repo.save(d);
        }).orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
