// this is the service class that implements business logic to the person list
package com.api2.contacts.service;

import com.api2.contacts.model.Person;
import com.api2.contacts.repository.PersonRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

// service annotation
@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    // get person based on the specific id:
    public String getPerson(Long id) {
        JSONObject returnObj = new JSONObject();

        // identify the person object from the repository based on the id:
        Person person = personRepository.findPersonById(id);

        // populate the JSON return object with the fields of the found person:
        returnObj.put("id", person.getId());
        returnObj.put("First Name", person.getFirstName());
        returnObj.put("Last Name", person.getLastName());
        returnObj.put("Address", person.getAddress());
        returnObj.put("Email", person.getEmail());
        returnObj.put("Phone", person.getPhone());

        return returnObj.toString();
    }

    // retrieve all entries from the repository:
    public String getAllPeople() {
        JSONObject returnObj = new JSONObject();
        JSONArray returnArr = new JSONArray();

        // gets all entries from the repository and puts in an array list:
        ArrayList<Person> allPeople = personRepository.findAll();
        for (Person currPerson: allPeople) {
            JSONObject currObj = new JSONObject();
            currObj.put("id", currPerson.getId());
            currObj.put("First Name", currPerson.getFirstName());
            currObj.put("Last Name", currPerson.getLastName());
            currObj.put("Address", currPerson.getAddress());
            currObj.put("Email", currPerson.getEmail());
            currObj.put("Phone", currPerson.getPhone());

            returnArr.put(currObj);
        }
        returnObj.put("content", returnArr);
        return returnObj.toString();
    }

    // create a new person entry and insert in the repository:
    public String createPerson(String payload) throws Exception { // if one field missing, throw exception
        JSONObject payloadObj = new JSONObject(payload);
        if ( !payloadObj.has("First Name") || !payloadObj.has("Last Name") ||
                !payloadObj.has("Address") || !payloadObj.has("Email") || !payloadObj.has("Phone") ) {
            throw new Exception("Full name, address, email and phone must be present");
        }
        // populate the person fields:
        Person person = new Person();
        person.setFirstName(payloadObj.getString("First Name"));
        person.setLastName(payloadObj.getString("Last Name"));
        person.setAddress(payloadObj.getString("Address"));
        person.setEmail(payloadObj.getString("Email"));
        person.setPhone(payloadObj.getString("Phone"));

        personRepository.save(person);
        return payloadObj.toString();
    }

    // delete a person from the repository:
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    // update a person's fields:
    public String updatePerson(Long id, String payload) {
        // identify the person object with the id from the repository:
        Person person = personRepository.findPersonById(id);
        // create JSON object from the payload:
        JSONObject updateObj = new JSONObject(payload);
        // update the existing object with the updates:
        person.setFirstName(updateObj.getString("First Name"));
        person.setLastName(updateObj.getString("Last Name"));
        person.setAddress(updateObj.getString("Address"));
        person.setEmail(updateObj.getString("Email"));
        person.setPhone(updateObj.getString("Phone"));

        // save back to the repository:
        personRepository.save(person);
        return updateObj.toString();
    }
}
