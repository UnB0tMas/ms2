// src/main/java/com/example/registropersonasgym/services/ClienteService.java
package com.example.registropersonasgym.services;

import com.example.registropersonasgym.models.Cliente;
import com.example.registropersonasgym.models.Persona;
import com.example.registropersonasgym.events.ClienteEventProducer;
import com.example.registropersonasgym.repositories.ClienteRepository;
import com.example.registropersonasgym.repositories.PersonaRepository;
import com.gym.events.dto.ClienteEventDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;
    private final PersonaRepository personaRepo;
    private final ClienteEventProducer clienteEventProducer;

    public ClienteService(
            ClienteRepository clienteRepo,
            PersonaRepository personaRepo,
            ClienteEventProducer clienteEventProducer
    ) {
        this.clienteRepo = clienteRepo;
        this.personaRepo = personaRepo;
        this.clienteEventProducer = clienteEventProducer;
    }

    public List<Cliente> getAll() {
        return clienteRepo.findAll();
    }

    public Optional<Cliente> getById(Integer id) {
        return clienteRepo.findById(id);
    }

    @Transactional
    public Cliente create(Cliente c) {
        Persona persona = c.getPersona();
        if (persona.getPersonaId() == null) {
            persona = personaRepo.save(persona);
        } else {
            persona = personaRepo.findById(persona.getPersonaId())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        }
        c.setPersona(persona);
        Cliente saved = clienteRepo.save(c);

        ClienteEventDTO event = new ClienteEventDTO(
                saved.getClienteId(),
                persona.getNombre() + " " + persona.getApellidoP() + " " + persona.getApellidoM(),
                persona.getNdeDocumento(),
                saved.getPesoInicio(),
                saved.getMembresia() != null ? saved.getMembresia().getMembresiaId() : null,
                "CREATED"
        );
        clienteEventProducer.sendClienteEvent(event);

        return saved;
    }

    @Transactional
    public Cliente update(Integer id, Cliente cambios) {
        return clienteRepo.findById(id).map(c -> {
            c.setPersona(cambios.getPersona());
            c.setPesoInicio(cambios.getPesoInicio());
            c.setImgInicio(cambios.getImgInicio());
            c.setMembresia(cambios.getMembresia());
            Cliente updated = clienteRepo.save(c);

            Persona persona = updated.getPersona();
            ClienteEventDTO event = new ClienteEventDTO(
                    updated.getClienteId(),
                    persona.getNombre() + " " + persona.getApellidoP() + " " + persona.getApellidoM(),
                    persona.getNdeDocumento(),
                    updated.getPesoInicio(),
                    updated.getMembresia() != null ? updated.getMembresia().getMembresiaId() : null,
                    "UPDATED"
            );
            clienteEventProducer.sendClienteEvent(event);

            return updated;
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Transactional
    public void delete(Integer id) {
        clienteRepo.findById(id).ifPresent(c -> {
            c.setEstado(false);
            Cliente updated = clienteRepo.save(c);

            Persona persona = updated.getPersona();
            ClienteEventDTO event = new ClienteEventDTO(
                    updated.getClienteId(),
                    persona.getNombre() + " " + persona.getApellidoP() + " " + persona.getApellidoM(),
                    persona.getNdeDocumento(),
                    updated.getPesoInicio(),
                    updated.getMembresia() != null ? updated.getMembresia().getMembresiaId() : null,
                    "DELETED"
            );
            clienteEventProducer.sendClienteEvent(event);
        });
    }
}
