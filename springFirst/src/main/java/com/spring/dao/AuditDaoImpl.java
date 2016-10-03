package com.spring.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.spring.model.Audit;

@Component
public class AuditDaoImpl implements AuditDao {

	private static final Logger logger = Logger.getLogger(AuditDaoImpl.class.getName());

	private JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String create(String request) {
		String query = "insert into audit_request (request, requested_date) values (?, CURDATE())";
		jdbcTemplate.update(query, new Object[] { request });

		String response = "Audit record successfully inserted!";

		logger.info(response);

		return response;
	}

	@Override
	public List<String> getAuditRequests() {
		logger.info("Calling getAuditRequests in AuditDaoImpl");
		String query = "select request from audit_request where requested_date = CURDATE()";

		List<String> responseList = jdbcTemplate.queryForList(query, String.class);
		return responseList;
	}

	public List<Audit> getAllAuditRequests() {
		logger.info("Calling getAllAuditRequests in AuditDaoImpl");
		String query = "select * from audit_request";

		List<Audit> auditList = jdbcTemplate.query(query, new AuditMapper());
		return auditList;
	}
	
}
