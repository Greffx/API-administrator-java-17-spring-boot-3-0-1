package com.eduardogreff.api.mapper;

import com.eduardogreff.domain.entities.Person;
import com.eduardogreff.domain.entities.dto.PersonDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonMapper {

    private final ModelMapper mapper = new ModelMapper();

    public Person toPerson(PersonDTO dto) {
        return mapper.map(dto, Person.class);
    }

    public PersonDTO personToPersonDTO(Person person) {
        return mapper.map(person, PersonDTO.class);
    }

    public List<PersonDTO> listOfPeopleToPeopleDTO(List<Person> people) {
        return people.stream().map(this::personToPersonDTO).toList();
    }

}
