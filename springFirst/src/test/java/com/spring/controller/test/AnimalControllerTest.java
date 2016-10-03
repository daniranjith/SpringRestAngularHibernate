package com.spring.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.spring.controller.AnimalController;
import com.spring.model.AnimalRequest;
import com.spring.model.Animals;
import com.spring.model.Animals.Animal;
import com.spring.service.AnimalService;
import com.spring.service.AuditService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*/WEB-INF/spring-servlet.xml" })
public class AnimalControllerTest {

	AnimalController controller;

	AuditService mockAuditService;
	AnimalService mockAnimalService;

	@Before
	public void setup() {
		controller = new AnimalController();
		mockAuditService = mock(AuditService.class);
		mockAnimalService = mock(AnimalService.class);
		ReflectionTestUtils.setField(controller, "auditService", mockAuditService);
		ReflectionTestUtils.setField(controller, "animalService", mockAnimalService);
	}

	@Test
	public void testGetCountriesPositive() throws Exception {
		try {

			// positive test case
			List<String> nameList = new ArrayList<>();
			nameList.add("Lion");
			nameList.add("Jersey");
			nameList.add("Tiger");
			boolean positiveFlag = true;

			AnimalRequest request = new AnimalRequest();
			request.setNameList(nameList);

			Animal mockAnimal = new Animal();
			mockAnimal.setName("Lion");
			mockAnimal.setCountryOfOrigin("india");
			mockAnimal.setName("Jersey");
			mockAnimal.setCountryOfOrigin("india");
			mockAnimal.setName("Tiger");
			mockAnimal.setCountryOfOrigin("india");
			List<Animal> mockAnimalList = new ArrayList<>();
			mockAnimalList.add(mockAnimal);
			
			Animals mockAnimals = new Animals();
			mockAnimals.setAnimal(mockAnimalList);
			
			when(mockAuditService.validateRequest(request)).thenReturn(positiveFlag);
			when(mockAuditService.create(nameList)).thenReturn(Matchers.anyString());
			when(mockAnimalService.getCountries(nameList)).thenReturn(mockAnimals);
			Animals passResultAnimals = (Animals) controller.getCountries(request);

			verify(mockAuditService, times(1)).validateRequest(request);
			verify(mockAuditService, times(1)).create(nameList);
			verify(mockAnimalService, times(1)).getCountries(nameList);
			assertEquals(mockAnimals, passResultAnimals);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetCountriesNegative() throws Exception {
		try {
			List<String> nameList = new ArrayList<>();
			nameList.add("Lion");

			AnimalRequest request = new AnimalRequest();
			request.setNameList(nameList);
			
			Animal mockAnimal = new Animal();
			mockAnimal.setName("Duplicate/Invalid Request");
			mockAnimal.setCountryOfOrigin("Duplicate Or Invalid Request : Please see log for more reason");
			List<Animal> mockAnimalList = new ArrayList<>();
			mockAnimalList.add(mockAnimal);
			
			Animals mockAnimals = new Animals();
			mockAnimals.setAnimal(mockAnimalList);
			
			when(mockAnimalService.returnDuplicateResponse()).thenReturn(mockAnimals);
			
			boolean negativeFlag = false;
			when(mockAuditService.validateRequest(request)).thenReturn(negativeFlag);
			
			Animals failResultAnimals = (Animals) controller.getCountries(request);
			List<Animal> failresultAnimal = failResultAnimals.getAnimal();
			
			verify(mockAuditService, times(1)).validateRequest(request);
			verify(mockAnimalService, times(1)).returnDuplicateResponse();
			assertTrue(failresultAnimal.get(0).getName().equals("Duplicate/Invalid Request"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
