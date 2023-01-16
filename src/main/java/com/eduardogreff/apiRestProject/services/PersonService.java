package com.eduardogreff.apiRestProject.services;

import com.eduardogreff.apiRestProject.entities.Person;
import com.eduardogreff.apiRestProject.entities.dto.PersonDTO;
import com.eduardogreff.apiRestProject.repositories.PersonRepository;
import com.eduardogreff.apiRestProject.services.exceptions.PersonNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(Long id) {
        Optional<Person> person = repository.findById(id);
        return person.orElseThrow(() -> new PersonNotFound("This value is invalid, try another one."));
    }

    public void create(PersonDTO personDTO) {
        repository.save(new Person(personDTO));
    }
}
