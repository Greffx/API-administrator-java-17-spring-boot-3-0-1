package com.eduardogreff.api.controllers;

import com.eduardogreff.api.mapper.PersonMapper;
import com.eduardogreff.domain.entities.Person;
import com.eduardogreff.domain.entities.dto.PersonDTO;
import com.eduardogreff.domain.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonControllerTest {

    @InjectMocks
    private PersonController controller;

    @Mock
    private PersonMapper mapper;

    @Mock
    private PersonService service;

    private PersonDTO personDTO;
    private Person person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createPeople();
    }

    @Test
    void findAll() {
    }

    @Test
    void whenFindByIdThenShouldReturnSuccess() {
        when(service.findById(anyLong())).thenReturn(person);
        when(mapper.personToPersonDTO(person)).thenReturn(personDTO);

        ResponseEntity<PersonDTO> result = controller.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(PersonDTO.class, result.getBody().getClass());
        assertEquals(1L, result.getBody().getId());
        assertEquals("Eduardo", result.getBody().getFirstName());
        assertEquals("Greff", result.getBody().getLastName());
    }

    @Test
    void create() {
    }

    @Test
    void put() {
    }

    @Test
    void delete() {
    }

    private void createPeople() {
        person = new Person(1L, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23);
        personDTO = new PersonDTO(1L, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23);
    }
}