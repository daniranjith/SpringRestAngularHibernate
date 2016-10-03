package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Audit;

public class AuditMapper implements RowMapper {
	public Audit mapRow(ResultSet rs, int rowNum) throws SQLException {
		Audit response = new Audit();
		response.setRequestId(rs.getInt("request_id"));
		response.setRequest(rs.getString("request"));
		response.setDate(rs.getString("requested_date"));
		return response;
	}
}
