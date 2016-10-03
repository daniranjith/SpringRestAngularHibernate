package com.spring.controller;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.model.hibernate.Person;
import com.spring.service.hibernate.PersonService;

@Controller
public class PersonController {
	private static final Logger logger = Logger.getLogger(PersonController.class.getName());
	@Autowired
	private PersonService personService;
	@RequestMapping("/home")
	public String home() {
 		return "home";
 	}
	@RequestMapping(value="/person/{id}", method = RequestMethod.GET )
	public ResponseEntity<Person> getPersonById(@PathVariable("id") Integer id) {
		logger.info("person id = " + id);
		Person person = personService.getPersonById(id);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	@RequestMapping(value= "/person", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPersons() {
		List<Person> list = personService.getAllPersons();
		return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
	}
	@RequestMapping(value= "/person", method = RequestMethod.POST)
	public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
        boolean flag = personService.addPerson(person);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(person.getPid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value="/person/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		personService.updatePerson(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	@RequestMapping(value="/person/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id) {
		personService.deletePerson(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 