// the repository class to connect to the database
package com.api2.contacts.repository;

import com.api2.contacts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // we tell JPA that we need the implementation of these methods. JPA will provide this implementation.
    ArrayList<Person> findAll();
    Person findPersonById(Long id);
}
