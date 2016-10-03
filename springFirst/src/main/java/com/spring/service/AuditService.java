package com.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.spring.dao.AuditDaoImpl;
import com.spring.model.AnimalRequest;
import com.spring.model.Audit;

@Component
public class AuditService {

	private static final Logger logger = Logger.getLogger(AnimalService.class.getName());

	@Autowired
	AuditDaoImpl auditDao;

	@Autowired
	XmlConverter xmlConverter;

	public String create(List<String> request) {
		logger.info("Calling create in AuditService");
		StringBuilder sb = new StringBuilder();
		for (String str : request) {
			sb.append(str).append(" ");
		}
		return auditDao.create(sb.toString());
	}

	public boolean validateRequest(AnimalRequest animalsRequest) throws IOException, SAXException, JAXBException {
		logger.info("Calling validateRequest in AuditService");
		boolean validFlag = true;
		xmlConverter.convertRequestToXML(animalsRequest);
		if (xmlConverter.validateInputXml()) {
			List<String> requestList = new ArrayList<>();
			Iterator<String> iterator = animalsRequest.getNameList().iterator();
			while (iterator.hasNext()) {
				requestList.add(iterator.next().toLowerCase());
			}
			Collections.sort(requestList);
			//get all requests for the current date
			List<String> auditList = getAuditRequests();
			for (String requestStr : auditList) {
				List<String> elements = Arrays.asList(requestStr.toLowerCase().split(" "));
				Collections.sort(elements);
				//find duplicate request
				if (requestList.equals(elements)) {
					validFlag = false;
					break;
				}
			}
		} else {
			validFlag = false;
		}
		return validFlag;
	}

	// get only current date audit request
	public List<String> getAuditRequests() {
		logger.info("Calling getAuditRequests in AuditService");
		return auditDao.getAuditRequests();
	}
	
	// get all audit request
	public List<Audit> getAllAuditRequests() {
		logger.info("Calling getAllAuditRequests in AuditService");
		return auditDao.getAllAuditRequests();
	}
}
