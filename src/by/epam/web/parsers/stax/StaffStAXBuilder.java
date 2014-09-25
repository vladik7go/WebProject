package by.epam.web.parsers.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.epam.web.parsers.entities.Employee;
import by.epam.web.parsers.entities.EmployeeEnum;
import by.epam.web.parsers.entities.Manager;
import by.epam.web.parsers.entities.Operator;
import static by.epam.web.application.controller.Controller.LOG;

public class StaffStAXBuilder {
	private HashSet<Employee> staff = new HashSet<>();
	private XMLInputFactory inputFactory;

	public StaffStAXBuilder() {

		inputFactory = XMLInputFactory.newInstance();
	}

	public Set<Employee> getStaff() {
		return staff;
	}

	public void buildSetStaff(String fileName) {
		FileInputStream fis = null;
		XMLStreamReader reader = null;
		String name;
		try {
			fis = new FileInputStream(new File(fileName));
			reader = inputFactory.createXMLStreamReader(fis);

			while (reader.hasNext()) {// --StAX parsing
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (EmployeeEnum.valueOf(name.toUpperCase()) == EmployeeEnum.MANAGER) {
						Manager manager = buildManager(reader);
						staff.add(manager);
					} else if (EmployeeEnum.valueOf(name.toUpperCase()) == EmployeeEnum.OPERATOR) {
						Operator operator = buildOperator(reader);
						staff.add(operator);
					}
				}
			}

		} catch (XMLStreamException e) {
			LOG.error(e);
		} catch (FileNotFoundException e) {
			LOG.error(e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				LOG.error("Impossible to close file " + fileName + " : " + e);
			}
		}
	}

	private Manager buildManager(XMLStreamReader reader)
			throws XMLStreamException {// --Method for
		// manager
		Manager manager = new Manager();
		manager.setCity(reader.getAttributeValue(null,
				EmployeeEnum.CITY.getValue()));// --Gaining attribute for
												// Manager (CITY)
		manager.setPassword(reader.getAttributeValue(null,
				EmployeeEnum.PASSWORD.getValue()));// --Gaining
													// attribute for
													// Manager (PASS)
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (EmployeeEnum.valueOf(name.toUpperCase())) {
				case ID:
					manager.setId(getXMLText(reader));
					break;
				case FIRSTNAME:
					name = getXMLText(reader);
					manager.setFirstName(name);
					break;
				case SECONDNAME:
					name = getXMLText(reader);
					manager.setSecondName(name);
					break;
				case BONUS:
					name = getXMLText(reader);
					manager.setBonus(Integer.parseInt(name));
					break;
				case PASSWORD:
					name = getXMLText(reader);
					manager.setPassword(name);
					break;
				default:
					LOG.info("UnExisting element in tag Manager");
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (EmployeeEnum.valueOf(name.toUpperCase()) == EmployeeEnum.MANAGER) {
					return manager;
				}
				break;
			}
		}
		throw new XMLStreamException("Unexisting element in tag Staff");
	}

	private Operator buildOperator(XMLStreamReader reader)
			throws XMLStreamException {
		Operator operator = new Operator();
		operator.setCity(reader.getAttributeValue(null,
				EmployeeEnum.CITY.getValue()));
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (EmployeeEnum.valueOf(name.toUpperCase())) {
				case ID:
					operator.setId(getXMLText(reader));
					break;
				case FIRSTNAME:
					name = getXMLText(reader);
					operator.setFirstName(name);
					break;
				case SECONDNAME:
					name = getXMLText(reader);
					operator.setSecondName(name);
					break;
				case NUMBEROFTOOLS:
					name = getXMLText(reader);
					operator.setNumberOfTools(Integer.parseInt(name));
					break;
				default:
					LOG.info("Unexisting element in gag Operator");
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (EmployeeEnum.valueOf(name.toUpperCase()) == EmployeeEnum.OPERATOR) {
					return operator;
				}
				break;
			}
		}
		throw new XMLStreamException("Unexisting element in tag Staff");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
