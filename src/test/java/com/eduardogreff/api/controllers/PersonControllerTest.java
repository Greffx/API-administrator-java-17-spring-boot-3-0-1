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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonControllerTest {

    @InjectMocks
    private PersonController controller;

    @Mock
    private PersonMapper mapper;

    @Mock
    private PersonService service;

    private PersonDTO personDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createPersonDTO();
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
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

    private void createPersonDTO() {
        personDTO = mapper.personToPersonDTO(new Person(1L, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23));
    }
}