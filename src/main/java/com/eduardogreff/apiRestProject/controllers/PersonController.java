package com.eduardogreff.apiRestProject.controllers;

import com.eduardogreff.apiRestProject.entities.Person;
import com.eduardogreff.apiRestProject.entities.dto.PersonDTO;
import com.eduardogreff.apiRestProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
