package com.spring.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.spring.model.AnimalResponse;
import com.spring.model.Animals;
import com.spring.model.Animals.Animal;

@Component
public class AnimalDaoImpl implements AnimalDao {

	private static final Logger logger = Logger.getLogger(AnimalDaoImpl.class.getName());

	private JdbcTemplate jdbcTemplateObject;
	private NamedParameterJdbcTemplate namedParTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		this.namedParTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public String create(String name, String countryOfOrigin) {

		String query = "insert into animals (name, country_of_origin) values (?, ?)";
		jdbcTemplateObject.update(query, new Object[] { name, countryOfOrigin });

		String response = "Animal record successfully inserted!";

		logger.info(response);
		return response;
	}

	@Override
	public String update(AnimalResponse animalResponse) {
		String query = "update animals set name = ?, country_of_origin = ? where animal_id = ?";
		jdbcTemplateObject.update(query, new Object[] { animalResponse.getName(),
				animalResponse.getCountryOfOrigin(),
				animalResponse.getAnimalId() });

		String response = "Animal record successfully updated!";

		logger.info(response);

		return response;
	}

	public AnimalResponse getAnimal(int id) {
		logger.info("Calling listCountries in AnimalDao");
		String query = "select * from animals where animal_id = ?";
		AnimalResponse animalResponse = (AnimalResponse) jdbcTemplateObject.queryForObject(query, new Object[] { id },
				new AnimalResponseMapper());

		return animalResponse;
	}
	
	@Override
	public Animals getCountryByName(List<String> nameList) {
		logger.info("Calling listCountries in AnimalDao");

		Map<String, List<String>> paramMap = Collections.singletonMap("name", nameList);

		String query = "select name, country_of_origin from animals where name IN (:name)";
		Animals animals = new Animals();
		List<Animal> animalList = namedParTemplate.query(query, paramMap, new AnimalMapper());
		animals.setAnimal(animalList);
		return animals;
	}

	@Override
	public List<AnimalResponse> listAnimals() {
		logger.info("Calling listCountries in AnimalDao");
		String query = "select * from animals";
		List<AnimalResponse> responseList = jdbcTemplateObject.query(query, new AnimalResponseMapper());

		return responseList;

	}
}
