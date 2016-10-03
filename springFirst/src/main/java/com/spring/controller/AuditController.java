package com.spring.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Audit;
import com.spring.service.AuditService;

@RestController
public class AuditController {
	private static final Logger logger = Logger.getLogger(AuditController.class.getName());
	
	@Autowired
	AuditService auditService;
	
	@RequestMapping(value = "/audit", method = RequestMethod.GET)
	public ModelAndView getAuditRequests(ModelMap modelMap) {
		logger.info("Calling getAuditRequests in AuditController");
		List<Audit> auditList = auditService.getAllAuditRequests();
		modelMap.put("auditList", auditList);
		ModelAndView mav = new ModelAndView("audit");
		return mav;
	}
}
