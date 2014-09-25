package by.epam.web.parsers.dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.web.parsers.entities.Employee;
import by.epam.web.parsers.entities.Manager;
import by.epam.web.parsers.entities.Operator;
import static by.epam.web.application.controller.Controller.LOG;

public class DomParser {

	Manager manager = null;
	Operator operator = null;

	List<Employee> list = new ArrayList<Employee>();

	public DomParser() {// Конструктор
		
	}

	public List<Employee> domParsing(String xmlDocument) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;
		try {
			builder = factory.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			LOG.error(e);

		}
		File f = new File(xmlDocument);
		try {
			doc = builder.parse(f);

		} catch (SAXException e) {
			LOG.error(e);

		} catch (IOException e) {
			LOG.error(e);

		}

		Element root = doc.getDocumentElement();// Получаем корневой элемент, в
												// виде объекта класса Element
												// System.out.println("Root teg: "
												// + root.getTagName());

		// -------------------
		NodeList nList = root.getChildNodes();// Получаем коллекцию класса
												// NodeList, содержащую дочерние
												// элементы в виде объектов
												// класса Node
		// -------------------------------------------------------------------
		// int counter = 1;

		for (int i = 0; i < nList.getLength(); i++) {
			if (nList.item(i) instanceof Element) {
				// --Получаем имя дочернего (для корневого элемента root) нода
				System.out.print(nList.item(i).getNodeName());
				if (nList.item(i).getNodeName().equals("tns:manager")) {// --заполняем
																		// DTO
																		// manager
					manager = new Manager();

					// System.out.print(nList.item(i).getNodeName() + " " +
					// counter
					// + ":   ");
					// counter++;

					// --Получаем имена аттрибутов и значения аттрибутов
					NamedNodeMap attributes = nList.item(i).getAttributes();
					for (int k = 0; k < attributes.getLength(); k++) {
						String attrValue = (attributes.item(k).getNodeValue());
						if (attributes.item(k).getNodeName().equals("city")) {
							manager.setCity(attrValue);// присваиваем полю
														// значение
														// города
						} else if (attributes.item(k).getNodeName()
								.equals("pass")) {
							manager.setPassword(attrValue);// присваиваем полю
															// значение
							// пароля
						}
						// System.out.print(attributes.item(k).getNodeName() +
						// "=== ");
						// System.out.print(attrValue + " ");
					}
					// --Получаем имена и значения дочерних нодов и присваиваем
					// их
					// полям объекта DTO
					NodeList below = nList.item(i).getChildNodes();
					for (int j = 0; j < below.getLength(); j++) {

						if (below.item(j) instanceof Element) {
							if (below.item(j).getNodeName()
									.equals("tns:firstName")) {
								manager.setFirstName(below.item(j)
										.getTextContent());
							} else if (below.item(j).getNodeName()
									.equals("tns:id")) {
								manager.setId(below.item(j).getTextContent());
							} else if (below.item(j).getNodeName()
									.equals("tns:secondName")) {
								manager.setSecondName(below.item(j)
										.getTextContent());
							} else if (below.item(j).getNodeName()
									.equals("tns:bonus")) {
								String tmpStringBonus = below.item(j)
										.getTextContent();
								int tmpIntBonus = Integer
										.parseInt(tmpStringBonus);
								manager.setBonus(tmpIntBonus);

							}
							// System.out.print(below.item(j).getNodeName() +
							// "=");
							// System.out.print(below.item(j).getTextContent());

						}
					}
					// System.out.println("City= " + attrValue );
					System.out.println("");
					// System.out.println(manager);
					list.add(manager);
					// -------------------------------------------------------------------------------
				} else if (nList.item(i).getNodeName().equals("tns:operator")) {// --заполняем
																				// DTO
																				// operator

					operator = new Operator();

					// --Получаем имена аттрибутов и значения аттрибутов
					NamedNodeMap attributes = nList.item(i).getAttributes();
					for (int k = 0; k < attributes.getLength(); k++) {
						String attrValue = (attributes.item(k).getNodeValue());
						if (attributes.item(k).getNodeName().equals("city")) {
							operator.setCity(attrValue);// присваиваем полю
														// значение
														// города
						}

					}
					// --Получаем имена и значения дочерних нодов и присваиваем
					// их
					// полям объекта DTO
					NodeList below = nList.item(i).getChildNodes();
					for (int j = 0; j < below.getLength(); j++) {

						if (below.item(j) instanceof Element) {
							if (below.item(j).getNodeName()
									.equals("tns:firstName")) {
								operator.setFirstName(below.item(j)
										.getTextContent());
							} else if (below.item(j).getNodeName()
									.equals("tns:id")) {
								operator.setId(below.item(j).getTextContent());
							} else if (below.item(j).getNodeName()
									.equals("tns:secondName")) {
								operator.setSecondName(below.item(j)
										.getTextContent());
							} else if (below.item(j).getNodeName()
									.equals("tns:numberOfTools")) {
								String tmpStringNmbr = below.item(j)
										.getTextContent();
								int tmpIntNmbr = Integer
										.parseInt(tmpStringNmbr);
								operator.setNumberOfTools(tmpIntNmbr);

							}
							// System.out.print(below.item(j).getNodeName() +
							// "=");
							// System.out.print(below.item(j).getTextContent());

						}
					}
					// System.out.println("City= " + attrValue );
					System.out.println("");
					// System.out.println(operator);
					list.add(operator);

				}
			}

		}
		return list;
	}
}
