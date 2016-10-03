package com.spring.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "animal" })
@XmlRootElement(name = "animals")
public class Animals {

	@XmlElement(required = true)
	protected List<Animals.Animal> animal;

	public List<Animal> getAnimal() {
		if (animal == null) {
			animal = new ArrayList<Animals.Animal>();
		}
		return this.animal;
	}

	public void setAnimal(List<Animal> animal) {
		this.animal = animal;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "name", "countryOfOrigin" })
	public static class Animal {
		@XmlElement(required = true)
		protected String name;
		@XmlElement(name = "country_of_origin", required = true)
		protected String countryOfOrigin;

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
}
