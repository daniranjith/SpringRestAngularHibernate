package com.spring.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "countryOfOrigin" })
@XmlRootElement(name = "animal")
public class AnimalResponse {
	
	private int animalId;
	@XmlElement(required = true)
	private String name;
	@XmlElement(name = "country_of_origin", required = true)
	private String countryOfOrigin;

	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String value) {
		this.name = value;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String value) {
		this.countryOfOrigin = value;
	}

}
