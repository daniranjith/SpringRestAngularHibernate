package com.spring.dao.hibernate;

import java.util.List;

import com.spring.model.hibernate.Person;

public interface PersonDAO {
	List<Person> getAllPersons();

	Person getPersonById(int pid);

	boolean addPerson(Person person);

	void updatePerson(Person person);

	void deletePerson(int pid);

	boolean personExists(String name, String location);
}
