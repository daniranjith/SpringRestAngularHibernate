package com.spring.service.hibernate;

import java.util.List;

import com.spring.model.hibernate.Person;

public interface PersonService {
	List<Person> getAllPersons();

	Person getPersonById(int pid);

	boolean addPerson(Person person);

	void updatePerson(Person person);

	void deletePerson(int pid);
}
