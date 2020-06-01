// this is the class for the model of the data of the person. A standard POJO (plain old Java object) class
package com.api2.contacts.model;

import javax.persistence.*;

@Entity // an entity class. An instance of this class represents a row in the table:
@Table(name = "person") // the table name in mySQL
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // id is generated automatically
    // each field of these will become a column in the mysql table "person"
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
