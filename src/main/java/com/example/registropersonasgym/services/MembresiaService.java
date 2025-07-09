package com.example.registropersonasgym.services;

import com.example.registropersonasgym.models.Membresia;
import com.example.registropersonasgym.repositories.MembresiaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MembresiaService {

    private final MembresiaRepository repo;

    public MembresiaService(MembresiaRepository repo) {
        this.repo = repo;
    }

    public List<Membresia> getAll() {
        return repo.findAll();
    }

    public Optional<Membresia> getById(Integer id) {
        return repo.findById(id);
    }

    public List<Membresia> findByNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    @Transactional
    public Membresia create(Membresia m) {
        return repo.save(m);
    }

    @Transactional
    public Membresia update(Integer id, Membresia cambios) {
        return repo.findById(id).map(m -> {
            m.setNombre(cambios.getNombre());
            m.setDescripcion(cambios.getDescripcion());
            m.setDuracionMeses(cambios.getDuracionMeses());
            m.setPrecio(cambios.getPrecio());
            m.setFechaInicioVigencia(cambios.getFechaInicioVigencia());
            m.setFechaFinVigencia(cambios.getFechaFinVigencia());
            m.setEstado(cambios.getEstado());
            return repo.save(m);
        }).orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
