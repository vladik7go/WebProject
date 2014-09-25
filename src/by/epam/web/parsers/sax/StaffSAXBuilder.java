package by.epam.web.parsers.sax;

import java.io.IOException;
import java.util.Set;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.web.parsers.entities.Employee;
import static by.epam.web.application.controller.Controller.LOG;



public class StaffSAXBuilder {
	private Set<Employee> staff;
	private StaffHandler sh;
	private XMLReader reader;
	
	public StaffSAXBuilder() {//Конструктор


		sh = new StaffHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(sh);
		} catch (SAXException e) {
			LOG.error(e);
		}
		
		
	}
	public Set<Employee> getStaff(){
		return staff;
	}
	public void buildSetStaff(String fileName){
		try {
			reader.parse(fileName);
		} catch (IOException | SAXException e) {
			LOG.error(e);
		}
		staff = sh.getEmployee();
	}
}
