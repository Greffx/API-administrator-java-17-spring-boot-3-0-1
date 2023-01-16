package com.eduardogreff.apiRestProject.entities.dto;

import com.eduardogreff.apiRestProject.entities.Person;

public class PersonDTO {

    private Long id;
    private String Firstname;
    private String lastName;
    private String address;
    private String gender;
    private String email;
    private Integer age;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.Firstname = person.getFirstname();
        this.lastName = person.getLastName();
        this.address = person.getAddress();
        this.gender = person.getGender();
        this.email = person.getAddress();
        this.age = person.getAge();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
