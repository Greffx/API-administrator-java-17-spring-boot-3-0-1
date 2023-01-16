package com.eduardogreff.apiRestProject.repositories;

import com.eduardogreff.apiRestProject.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
