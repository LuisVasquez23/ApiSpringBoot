package org.example.personaapi.application.services.persona;

import org.example.personaapi.domain.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaServices extends JpaRepository<Persona , Long> {
}
