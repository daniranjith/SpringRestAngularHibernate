package com.spring.service.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.hibernate.PersonDAO;
import com.spring.model.hibernate.Person;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDAO personDAOImpl;

	@Override
	public Person getPersonById(int pid) {
		Person obj = personDAOImpl.getPersonById(pid);
		return obj;
	}

	@Override
	public List<Person> getAllPersons() {
		return personDAOImpl.getAllPersons();
	}

	@Override
	public synchronized boolean addPerson(Person person) {
		if (personDAOImpl.personExists(person.getName(), person.getLocation())) {
			return false;
		} else {
			personDAOImpl.addPerson(person);
			return true;
		}
	}

	@Override
	public void updatePerson(Person person) {
		personDAOImpl.updatePerson(person);
	}

	@Override
	public void deletePerson(int pid) {
		personDAOImpl.deletePerson(pid);
	}
}
