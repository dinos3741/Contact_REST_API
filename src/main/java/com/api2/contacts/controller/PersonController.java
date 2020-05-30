// this is the class where we annotate the paths (the API layer).
// It implements the API that processes HTTP verbs from the user and exposes endpoints.

package com.api2.contacts.controller;

import com.api2.contacts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

// RequestMapping creates an endpoint in the specific path that is inherited by all verbs
@RequestMapping("/")
@RestController
public class PersonController { // constructor
    @Autowired
    private PersonService mainService;

    // GET request for root:
    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome to my API</h1> <h2>Go to /contacts to authenticate and see the contact list</h2>");
    }

    // GET request retrieves all entries from the DB:
    //@JSONGetMapping(value = "/person")
    @GetMapping(path="/contacts", produces = "application/json")
    public String getPerson() {
        return mainService.getAllPeople();
    }

    // GET request with specific ID retrieves one entry
    //@JSONGetMapping(value = "/person/{id}")
    @GetMapping(path="/contacts/{id}", produces = "application/json")
    // PathVariable annotation means that the ID is found in the path
    public String getPerson(@PathVariable Long id) {
        return mainService.getPerson(id);
    }

    //  Add a person. The annotation PostMapping tells spring that this
    // method will be used to send POST requests. NonNull means that the field cannot be empty
    // RequestBody annotation means that we receive the person details from the body of the request
    //@JSONPostMapping(value = "/person")
    @PostMapping(path="/contacts", produces = "application/json")
    public String createPerson(@NonNull @RequestBody String payload) throws Exception {
        return mainService.createPerson(payload);
    }

    // Delete a person. DeleteMapping exposes a DELETE http verb
    @DeleteMapping(path="/contacts/{id}") // we pass the id from the path down to the service
    public void deletePerson(@PathVariable("id") Long id) {
        // path variable maps the variable id in the path to the variable id in the method
        mainService.deletePerson(id);
    }

    // Update details of a person.
    @PutMapping(path="/contacts/{id}")
    public String updatePerson(@NonNull @PathVariable("id") Long id, @RequestBody String payload) {
        return mainService.updatePerson(id, payload);
    }
}
