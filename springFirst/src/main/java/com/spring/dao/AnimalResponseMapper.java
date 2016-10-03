package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.spring.model.AnimalResponse;

public class AnimalResponseMapper implements RowMapper {
	public AnimalResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		AnimalResponse response = new AnimalResponse();
		response.setAnimalId(rs.getInt("animal_id"));
		response.setName(rs.getString("name"));
		
		response.setCountryOfOrigin(rs.getString("country_of_origin"));
		return response;
	}
}
