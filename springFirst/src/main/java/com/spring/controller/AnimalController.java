package com.spring.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.spring.exception.SpringException;
import com.spring.model.AnimalRequest;
import com.spring.model.AnimalResponse;
import com.spring.model.Animals;
import com.spring.service.AnimalService;
import com.spring.service.AuditService;

@RestController
public class AnimalController {

	private static final Logger logger = Logger.getLogger(AnimalController.class.getName());

	@Autowired
	AnimalService animalService;

	@Autowired
	AuditService auditService;

	@RequestMapping(value = "/home", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
	public ModelAndView loadHomePage() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
	public ModelAndView createAnimal() {
		ModelAndView mav = new ModelAndView("create");
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { MediaType.TEXT_HTML_VALUE })
	@ExceptionHandler({ SpringException.class })
	public ModelAndView createAnimal(@RequestParam String name, @RequestParam String countryOfOrigin,
			ModelMap modelMap) {
		String response = animalService.createAnimal(name, countryOfOrigin);
		modelMap.put("response", response);
		ModelAndView mav = new ModelAndView("create");
		return mav;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
	@ExceptionHandler({ SpringException.class })
	public ModelAndView getAnimal(@PathVariable int id, ModelMap modelMap) {
		AnimalResponse animalResponse = animalService.getAnimal(id);
		modelMap.put("animalResponse", animalResponse);
		ModelAndView mav = new ModelAndView("update");
		return mav;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = { MediaType.TEXT_HTML_VALUE })
	@ExceptionHandler({ SpringException.class })
	public ModelAndView updateAnimal(@ModelAttribute AnimalResponse response, ModelMap modelMap) {
		String animalResponse = animalService.updateAnimal(response);
		modelMap.put("response", animalResponse);
		ModelAndView mav = new ModelAndView("update");
		return mav;
	}

	@RequestMapping(value = "/animals", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
	@ExceptionHandler({ SpringException.class })
	public ModelAndView listAnimals(ModelMap modelMap) {
		List<AnimalResponse> animalList = animalService.getAnimals();
		modelMap.put("animalList", animalList);
		ModelAndView mav = new ModelAndView("animals");
		return mav;
	}

	@RequestMapping(value = "/countries", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE })
	public Animals getCountries(@RequestBody AnimalRequest animalsRequest) throws IOException, SAXException, JAXBException {
		boolean auditFlag = auditService.validateRequest(animalsRequest);
		if (auditFlag) {
			// create a audit record
			auditService.create(animalsRequest.getNameList());
			logger.info("Calling getCountries in AnimalController");
			// get countries based on names
			return animalService.getCountries(animalsRequest.getNameList());
		} else {
			// for duplicate/Invalid request
			return animalService.returnDuplicateResponse();
		}
	}
}
