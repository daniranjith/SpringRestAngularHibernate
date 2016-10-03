package com.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.web.client.RestTemplate;

import com.spring.model.AnimalRequest;
import com.spring.model.Animals;
import com.spring.model.Animals.Animal;

// Client for the rest controller
public class Main {
	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String arg[]) {
		logger.info("========================= REST CLIENT starts  ==========================");
		final String uri = "http://localhost:8080/countries";
		RestTemplate restTemplate = new RestTemplate();
		AnimalRequest request = case1();

		Animals response = restTemplate.postForObject(uri, request, Animals.class);

		for (Animal animal : response.getAnimal()) {
			logger.info(animal.getName() + " is from " + animal.getCountryOfOrigin());
		}
		logger.info("========================= REST CLIENT Ends  ==========================");
	}

	// Case 1:
	// One Animal names
	// Expected Result : One animal response with name & countryOfOrigin
	private static AnimalRequest case1() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("Lion");
		request.setNameList(nameList);
		return request;
	}

	// Case 2:
	// Two Animal names
	// Expected Result : Two animal responses with name & countryOfOrigin
	private static AnimalRequest case2() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("Lion");
		nameList.add("Tiger");
		request.setNameList(nameList);
		return request;
	}

	// Case 3:
	// Three Animal names
	// Expected Result : Three animal responses with name & countryOfOrigin
	private static AnimalRequest case3() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("Lion");
		nameList.add("Tiger");
		nameList.add("Cow");
		request.setNameList(nameList);
		return request;
	}

	// Case 4:
	// More than Three Animal names
	// Expected Result : Invalid Request.
	private static AnimalRequest case4() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("Lion");
		nameList.add("Tiger");
		nameList.add("Cow");
		nameList.add("giraffe");
		request.setNameList(nameList);
		return request;
	}

	// Case 5:
	// two same Animal names
	// Expected Result : One animal response with name & countryOfOrigin (the
	// same request should be ignored)
	private static AnimalRequest case5() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("Lion");
		nameList.add("Lion");
		request.setNameList(nameList);
		return request;
	}

	// Case 6:
	// three same Animal names
	// Expected Result : One animal response with name & countryOfOrigin (the
	// same request should be ignored)
	private static AnimalRequest case6() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("Lion");
		nameList.add("Lion");
		nameList.add("Lion");
		request.setNameList(nameList);
		return request;
	}

	// Case 7:
	// Reorder two same Animal names which was already requested for that same
	// day
	// Expected Result: Duplicate Request
	private static AnimalRequest case7() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("Tiger");
		nameList.add("Lion");
		request.setNameList(nameList);
		return request;
	}

	// Case 8:
	// Reorder three same Animal names which was already requested for that same
	// day
	// Expected Result: Duplicate Request
	private static AnimalRequest case8() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("cow");
		nameList.add("Tiger");
		nameList.add("Lion");
		request.setNameList(nameList);
		return request;
	}

	// Case 9:
	// Mix upper/lower case for two same Animal names which was already
	// requested for that same
	// day
	// Expected Result: Duplicate Request
	private static AnimalRequest case9() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("TiGEr");
		nameList.add("LiON");
		request.setNameList(nameList);
		return request;
	}

	// Case 10:
	// Mix upper/lower case for three same Animal names which was already
	// requested for that same
	// day
	// Expected Result: Duplicate Request
	private static AnimalRequest case10() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("TiGEr");
		nameList.add("LiON");
		nameList.add("COW");
		request.setNameList(nameList);
		return request;
	}

	// Case 11:
	// Zero Animal name
	// Expected Result: invalid request
	private static AnimalRequest case11() {
		AnimalRequest request = new AnimalRequest();
		return request;
	}

	// Case 12:
	// one Animal name which is not there in database
	// Expected Result: <animals />
	private static AnimalRequest case12() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("Peacock");
		request.setNameList(nameList);
		return request;
	}

	// Case 13:
	// one Animal name which is not there in db and one name which in db
	// Expected Result: return animal for the one which is there in db
	private static AnimalRequest case13() {
		AnimalRequest request = new AnimalRequest();
		List<String> nameList = new ArrayList<>();
		nameList.add("Tiger");
		nameList.add("Peacock");
		request.setNameList(nameList);
		return request;
	}
}
