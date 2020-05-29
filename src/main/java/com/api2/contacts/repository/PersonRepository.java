package com.api2.contacts.repository;

import com.api2.contacts.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PersonRepository extends CrudRepository<Person, Long> {
    ArrayList<Person> findAll();
    Person findPersonById(Long id);
}
