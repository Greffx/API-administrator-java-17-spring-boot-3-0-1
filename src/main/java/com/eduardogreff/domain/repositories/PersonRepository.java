package com.eduardogreff.domain.repositories;

import com.eduardogreff.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
