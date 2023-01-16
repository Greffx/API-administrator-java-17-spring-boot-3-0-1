package com.eduardogreff.apiRestProject.controllers;

import com.eduardogreff.apiRestProject.entities.dto.PersonDTO;
import com.eduardogreff.apiRestProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(PersonDTO::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new PersonDTO(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PersonDTO personDTO) {
        service.create(personDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(personDTO.getId()).toUri()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@RequestBody PersonDTO dto, @PathVariable Long id) {
        service.put(dto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
