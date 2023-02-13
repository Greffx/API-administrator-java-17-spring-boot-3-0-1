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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
    void whenFindAllThenShouldReturnAListOfPersonDTO() {
        when(service.findAll()).thenReturn(List.of(person));
        when(mapper.listOfPeopleToPeopleDTO(any())).thenReturn(List.of(personDTO));

        ResponseEntity<List<PersonDTO>> result = controller.findAll();

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(PersonDTO.class, result.getBody().get(0).getClass());
        assertEquals(1, result.getBody().size());
        assertEquals("Eduardo", result.getBody().get(0).getFirstName());
        assertEquals("Greff", result.getBody().get(0).getLastName());
        assertEquals("male", result.getBody().get(0).getGender());
        assertEquals(1L, result.getBody().get(0).getId());
    }

    @Test
    void whenFindByIdThenShouldReturnSuccess() {
        when(service.findById(anyLong())).thenReturn(person);
        when(mapper.personToPersonDTO(person)).thenReturn(personDTO);

        ResponseEntity<PersonDTO> result = controller.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(PersonDTO.class, result.getBody().getClass());
        assertEquals(1L, result.getBody().getId());
        assertEquals("Eduardo", result.getBody().getFirstName());
        assertEquals("Greff", result.getBody().getLastName());
    }

    @Test
    void whenCreateThenShouldReturnCreated() {
        when(service.create(any())).thenReturn(person);

        ResponseEntity<PersonDTO> result = controller.create(personDTO);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getHeaders().get("Location"));
        assertEquals(ResponseEntity.class, result.getClass());

    }

    @Test
    void whenUpdateThenShouldReturnSuccess() {
        when(service.update(anyLong(), any())).thenReturn(person);
        when(mapper.personToPersonDTO(any())).thenReturn(personDTO);

        ResponseEntity<PersonDTO> result = controller.put(1L, personDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(PersonDTO.class, result.getBody().getClass());
        assertEquals("Eduardo", result.getBody().getFirstName());
        assertEquals("Greff", result.getBody().getLastName());
        assertEquals("male", result.getBody().getGender());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).deleteById(anyLong());

        controller.delete(1L);

        verify(service, times(1)).deleteById(1L);
    }

    private void createPeople() {
        person = new Person(1L, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23);
        personDTO = new PersonDTO(1L, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23);
    }
}