package com.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import com.spring.model.AnimalResponse;
import com.spring.model.Animals;

public interface AnimalDao {

	// To Initialize the datastore
	public void setDataSource(DataSource dataSource);

	// To create animal record in animals table
	public String create(String name, String countryOfOrigin);

	// To update animal record in animals table
	public String update(AnimalResponse animalResponse);

	// to get animal by id
	public AnimalResponse getAnimal(int id);
	
	// to get all animals
	public List<AnimalResponse> listAnimals();

	// to get Countries by animal name
	public Animals getCountryByName(List<String> nameList);

}
