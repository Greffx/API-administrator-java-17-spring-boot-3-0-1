package com.eduardogreff.api.config;

import com.eduardogreff.domain.entities.Book;
import com.eduardogreff.domain.entities.Person;
import com.eduardogreff.domain.repositories.BookRepository;
import com.eduardogreff.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;

@Configuration
@Profile("testH2")
public class ObjectsForDB {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public void objectsToUseForStudiesPurposes() throws ParseException {

        personRepository.deleteAll();
        bookRepository.deleteAll();

        Person person = new Person(null, "Eduardo", "Greff", "Porto Alegre", "male", "eduardo1@gmail.com", 23);
        Person person1 = new Person(null, "Rebeca", "Silva", "Kansas", "female", "rebeca1@gmail.com", 21);
        Person person2 = new Person(null, "Jonas", "Grafeno", "Berlin", "male", "jonas1@gmail.com", 32);
        Person person3 = new Person(null, "Pedro", "Pedroso", "France", "male", "pedro1@gmail.com", 12);
        Person person4 = new Person(null, "Gabriel", "Ferraz", "Porto", "male", "gabriel1@gmail.com", 53);
        Person person5 = new Person(null, "Marcela", "Junior", "Canoas", "female", "marcela1@gmail.com", 33);

        personRepository.saveAll(Arrays.asList(person, person1, person2, person3, person4, person5));

        Book book = new Book(null, "Harry Potter", "Jk", new BigDecimal("23.56"));
        Book book1 = new Book(null, "IT", "Stephen King", new BigDecimal("42.56"));
        Book book2 = new Book(null, "Game Of Thrones", "Charles", new BigDecimal("11.56"));
        Book book3 = new Book(null, "Animal Farm", "George Orwell", new BigDecimal("50.56"));


        bookRepository.saveAll(Arrays.asList(book, book1, book2, book3));
    }
}
