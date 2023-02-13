package com.eduardogreff.domain.services;

import com.eduardogreff.api.mapper.PersonMapper;
import com.eduardogreff.domain.entities.Book;
import com.eduardogreff.domain.entities.Person;
import com.eduardogreff.domain.entities.dto.PersonDTO;
import com.eduardogreff.domain.repositories.PersonRepository;
import com.eduardogreff.domain.services.exceptions.PersonNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    void whenFindAllThenShouldReturnAListOfPeople() {
        when(repository.findAll()).thenReturn(List.of(person));

        List<Person> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Person.class, result.get(0).getClass());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Eduardo", result.get(0).getFirstName());
        assertEquals("Greff", result.get(0).getLastName());
    }

    @Test
    void whenFindByIdThenShouldReturnAPersonInstance() {
        when(repository.findById(Mockito.anyLong())).thenReturn(optionalPerson);

        Person result = service.findById(1L);

        assertNotNull(result);
        assertEquals(Person.class, result.getClass());
        assertEquals(1L, result.getId());
        assertEquals("Eduardo", result.getFirstName());
        assertEquals("Greff", result.getLastName());
        assertEquals("Porto Alegre", result.getCity());
    }

    @Test
    void whenFindByIdThenShouldReturnPersonNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(new PersonNotFound("This value is invalid, try another one."));

        try {
            service.findById(2L);
        } catch (Exception e) {
            assertEquals(PersonNotFound.class, e.getClass());
            assertEquals("This value is invalid, try another one.", e.getMessage());
        }
    }


    @Test
    void whenCreateThenShouldReturnSuccess() {
        when(repository.save(any())).thenReturn(person);

        Person result = service.create(personDTO);

        assertNotNull(result);
        assertEquals(Person.class, result.getClass());
        assertEquals(1L, result.getId());
        assertEquals("Eduardo", result.getFirstName());
        assertEquals("Greff", result.getLastName());
        assertEquals("male", result.getGender());
    }

    @Test
    void deleteByIdWithSuccess() {
        when(repository.findById(anyLong())).thenReturn(optionalPerson);
        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(1L);

        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteByIdWithPersonNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(new PersonNotFound("This value is invalid, try another one."));

        try {
            service.deleteById(1L);
        } catch (PersonNotFound e) {
            assertEquals(PersonNotFound.class, e.getClass());
            assertEquals("This value is invalid, try another one.", e.getMessage());
        }
    }

    private void createPeople() {
        person = new Person(1L, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23);
        personDTO = mapper.personToPersonDTO(person);
        optionalPerson = Optional.of(person);
    }
}