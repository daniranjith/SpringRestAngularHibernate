package com.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement (name = "animal")
@XmlAccessorType(XmlAccessType.NONE)
@Component
public class AnimalRequest  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int animalId;
	private List<String> namelist;
	
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	public List<String> getNameList() {
		return namelist;
	}
	
	@XmlElement(name = "name", required = true)
	public void setNameList(List<String> nameList) {
		this.namelist = nameList;
	}
}
