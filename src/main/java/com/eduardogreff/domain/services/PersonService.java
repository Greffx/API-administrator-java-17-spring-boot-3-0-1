package com.eduardogreff.domain.services;

import com.eduardogreff.domain.entities.Person;
import com.eduardogreff.domain.entities.dto.PersonDTO;
import com.eduardogreff.api.mapper.PersonMapper;
import com.eduardogreff.domain.repositories.PersonRepository;
import com.eduardogreff.domain.services.exceptions.PersonNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;

    public List<Person> findAll() {
        return (repository.findAll());
    }

    public Person findById(Long id) {
        return (repository.findById(id)
                .orElseThrow(() -> new PersonNotFound("This value is invalid, try another one.")));
    }

    public Person create(PersonDTO personDTO) {
        return repository.save(mapper.toPerson(personDTO));
    }

    public Person put(PersonDTO dto, Long id) {
        dto.setId(id);
        return repository.save(mapper.toPerson(dto));
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
