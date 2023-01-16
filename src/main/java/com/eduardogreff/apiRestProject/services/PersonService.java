package com.eduardogreff.apiRestProject.services;

import com.eduardogreff.apiRestProject.entities.Person;
import com.eduardogreff.apiRestProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public List<Person> findAll() {
        return repository.findAll();
    }
}
