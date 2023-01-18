package com.eduardogreff.services;

import com.eduardogreff.entities.Person;
import com.eduardogreff.entities.dto.PersonDTO;
import com.eduardogreff.entities.dto.mapper.PersonMapper;
import com.eduardogreff.repositories.PersonRepository;
import com.eduardogreff.services.exceptions.PersonNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public List<PersonDTO> findAll() {
        return PersonMapper.listOfPeopleToPeopleDTO(repository.findAll());
    }

    public PersonDTO findById(Long id) {
        return PersonMapper.personToPersonDTO(repository.findById(id)
                .orElseThrow(() -> new PersonNotFound("This value is invalid, try another one.")));
    }

    public void create(PersonDTO personDTO) {
        repository.save(PersonMapper.toPerson(personDTO));
    }

    public void put(PersonDTO dto, Long id) {
//        findById(id);
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
//        findById(id);
        repository.deleteById(id);
    }
}
