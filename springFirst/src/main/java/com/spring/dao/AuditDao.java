package com.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import com.spring.model.Audit;

public interface AuditDao {

	// To Initialize the datastore
	public void setDataSource(DataSource dataSource);

	// To create request
	public String create(String request);

	// To get one day audit request
	public List<String> getAuditRequests();
	
	// To get all audit request
	public List<Audit> getAllAuditRequests();
}
