package com.tyutjohn.test.service;

import com.tyutjohn.test.bean.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPerson();
    String addPerson(Person person);
}
