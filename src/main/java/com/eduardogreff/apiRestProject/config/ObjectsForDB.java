package com.eduardogreff.apiRestProject.config;

import com.eduardogreff.apiRestProject.entities.Person;
import com.eduardogreff.apiRestProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("mysqldb")
public class ObjectsForDB {

    @Autowired
    private PersonRepository personRepository;

    @Bean
    public void objectsToUseForStudiesPurposes() {

        personRepository.deleteAll();

        Person person = new Person(null, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23);
        Person person1 = new Person(null, "Rebeca", "Silva", "Kansas", "female", "rebeca1@gmail.com", 21);
        Person person2 = new Person(null, "Jonas", "Grafeno", "Berlin", "male", "jonas1@gmail.com", 32);
        Person person3 = new Person(null, "Pedro", "Pedroso", "France", "male", "pedro1@gmail.com", 12);
        Person person4 = new Person(null, "Gabriel", "Ferraz", "Porto", "male", "gabriel1@gmail.com", 53);
        Person person5 = new Person(null, "Marcela", "Junior", "Canoas", "female", "marcela1@gmail.com", 33);

        personRepository.saveAll(Arrays.asList(person, person1, person2, person3, person4, person5));
    }
}
