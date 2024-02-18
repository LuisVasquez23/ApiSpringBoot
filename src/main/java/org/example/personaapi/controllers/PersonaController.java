package org.example.personaapi.controllers;

import org.example.personaapi.application.services.persona.PersonaServices;
import org.example.personaapi.domain.models.BaseResponseModel;
import org.example.personaapi.domain.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personas")
public class PersonaController {

    @Autowired
    private PersonaServices personaServices;

    @GetMapping
    public BaseResponseModel getAllPersonas() {
        List<Persona> personas = personaServices.findAll();
        return new BaseResponseModel(200, true, "Success", personas);
    }
    @PostMapping
    public BaseResponseModel createPersona(@RequestBody Persona persona) {
        Persona newPersona = personaServices.save(persona);
        return new BaseResponseModel(201, true, "Person created successfully", newPersona);
    }

    @PutMapping("/{id}")
    public BaseResponseModel updatePersona(@RequestBody Persona newPersona, @PathVariable Long id) {
        return personaServices.findById(id)
                .map(persona -> {
                    persona.setNombre(newPersona.getNombre());
                    persona.setApellido(newPersona.getApellido());
                    persona.setEmail(newPersona.getEmail());
                    personaServices.save(persona);
                    return new BaseResponseModel(200, true, "Person updated successfully", persona);
                })
                .orElseGet(() -> new BaseResponseModel(404, false, "Person not found", null));
    }
    @DeleteMapping("/{id}")
    public BaseResponseModel deletePersona(@PathVariable Long id) {
        personaServices.deleteById(id);
        return new BaseResponseModel(200, true, "Person deleted successfully", null);
    }
}
