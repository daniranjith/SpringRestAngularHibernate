package com.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.dao.AnimalDaoImpl;
import com.spring.model.AnimalResponse;
import com.spring.model.Animals;
import com.spring.model.Animals.Animal;

@Component
public class AnimalService {
	private static final Logger logger = Logger.getLogger(AnimalService.class.getName());

	@Autowired
	AnimalDaoImpl animalDao;

	@Autowired
	XmlConverter xmlConverter;

	public String createAnimal(String name, String countryOfOrigin) {
		logger.info("Calling createAnimal in AnimalService");
		return animalDao.create(name, countryOfOrigin);
	}

	public AnimalResponse getAnimal(int id) {
		logger.info("Calling getAnimal in AnimalService");
		return animalDao.getAnimal(id);
	}

	public String updateAnimal(AnimalResponse animalResponse) {
		logger.info("Calling updateAnimal in AnimalService");
		return animalDao.update(animalResponse);
	}

	public List<AnimalResponse> getAnimals() {
		logger.info("Calling getAnimals in AnimalService");
		return animalDao.listAnimals();
	}

	public Animals getCountries(List<String> nameList) throws IOException {
		logger.info("Calling getCountries in AnimalService");
		// Remove duplicates from list
		Set<String> nameSet = new LinkedHashSet<>();
		nameSet.addAll(nameList);
		logger.info("Set size = " + nameSet.size());
		Animals animals = animalDao.getCountryByName(new ArrayList<String>(nameSet));
		xmlConverter.convertResponseToXML(animals);
		return animals;
	}

	public Animals returnDuplicateResponse() throws IOException {
		logger.info("!!!!!!!!!!!!!!!!!! DUPLICATE/INVALID REQUEST !!!!!!!!!!!!!!!!!!!!");
		List<Animal> animalList = new ArrayList<>();
		Animal animalObj = new Animal();
		animalObj.setName("Duplicate/Invalid Request");
		animalObj.setCountryOfOrigin("Duplicate Or Invalid Request : Please see log for more reason");
		animalList.add(animalObj);
		Animals animals = new Animals();
		animals.setAnimal(animalList);
		xmlConverter.convertResponseToXML(animals);
		return animals;
	}
}
