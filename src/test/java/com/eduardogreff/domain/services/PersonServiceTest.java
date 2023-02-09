package com.eduardogreff.domain.services;

import com.eduardogreff.api.mapper.PersonMapper;
import com.eduardogreff.domain.entities.Person;
import com.eduardogreff.domain.entities.dto.PersonDTO;
import com.eduardogreff.domain.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PersonServiceTest {

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @Mock
    private PersonMapper mapper;

    private Person person;

    private PersonDTO personDTO;

    private Optional<Person> optionalPerson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createPeople();
    }

    @Test
    void findAll() {
    }

    @Test
    void whenFindByIdThenShouldReturnAPersonInstance() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalPerson);
        Person result = service.findById(1L);

        assertNotNull(result);
        assertEquals(Person.class, result.getClass());
        assertEquals(1L, result.getId());
        assertEquals("Eduardo", result.getFirstName());
        assertEquals("Greff", result.getLastName());
        assertEquals("Porto Alegre", result.getCity());
    }

    @Test
    void create() {
    }

    @Test
    void put() {
    }

    @Test
    void deleteById() {
    }

    private void createPeople() {
        person = new Person(1L, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23);
        personDTO = mapper.personToPersonDTO(new Person(1L, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23));
        optionalPerson = Optional.of(new Person(1L, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23));

    }
}