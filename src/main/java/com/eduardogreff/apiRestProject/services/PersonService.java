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

    public void put(PersonDTO dto, Long id) {
        findById(id);
        Person monitoredPerson = repository.getReferenceById(id);
        updatePerson(monitoredPerson, dto);
        repository.save(monitoredPerson);
    }

    private void updatePerson(Person monitoredPerson, PersonDTO dto) {
        monitoredPerson.setFirstName(dto.getFirstName());
        monitoredPerson.setLastName(dto.getLastName());
        monitoredPerson.setCity(dto.getCity());
        monitoredPerson.setGender(dto.getGender());
        monitoredPerson.setEmail(dto.getEmail());
        monitoredPerson.setAge(dto.getAge());
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
