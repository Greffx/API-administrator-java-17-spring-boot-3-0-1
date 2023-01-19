package com.eduardogreff.api.mapper;

import com.eduardogreff.domain.entities.Person;
import com.eduardogreff.domain.entities.dto.PersonDTO;
import org.modelmapper.ModelMapper;

import java.util.List;

public class PersonMapper {

    private static final ModelMapper mapper = new org.modelmapper.ModelMapper();

    public static Person toPerson(PersonDTO dto) {
        return mapper.map(dto, Person.class);
    }

    public static PersonDTO personToPersonDTO(Person person) {
        return mapper.map(person, PersonDTO.class);
    }

    public static List<PersonDTO> listOfPeopleToPeopleDTO(List<Person> people) {
        return people.stream().map(person -> new PersonDTO()).toList();
    }

}
