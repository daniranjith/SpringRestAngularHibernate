package com.spring.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.spring.model.AnimalRequest;
import com.spring.model.Animals;

@Component
public class XmlConverter {
	private static final Logger logger = Logger.getLogger(XmlConverter.class.getName());

	public void convertRequestToXML(AnimalRequest animalRequest) throws IOException {

		try {

			File file = new File("input.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(AnimalRequest.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(animalRequest, file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void convertResponseToXML(Animals animals) throws IOException {

		try {

			File file = new File("output.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Animals.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(animals, file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public boolean validateInputXml() throws SAXException, IOException {
		boolean validFlag = false;
		Source xmlFile = new StreamSource(new FileReader("input.xml"));
		Source xmlSchema = new StreamSource(new FileReader("request_animals.xsd"));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xmlSchema);
		Validator validator = schema.newValidator();
		try {
			validator.validate(xmlFile);
			validFlag = true;
			logger.info("Input XML is VALID");
		} catch (SAXException e) {
			validFlag = false;
			logger.info("Input XML is NOT VALID with REASON : " + e.getLocalizedMessage());
		}
		return validFlag;
	}
}