package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Animals.Animal;

public class AnimalMapper implements RowMapper {
	public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Animal animal = new Animal();
		animal.setName(rs.getString("name"));
		animal.setCountryOfOrigin(rs.getString("country_of_origin"));
		return animal;
	}
}
