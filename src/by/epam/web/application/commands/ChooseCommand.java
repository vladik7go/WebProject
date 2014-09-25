package by.epam.web.application.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.resource.ConfigurationManager;
import by.epam.web.parsers.dom.DomParser;
import by.epam.web.parsers.entities.Employee;
import by.epam.web.parsers.sax.StaffSAXBuilder;
import by.epam.web.parsers.stax.StaffStAXBuilder;

public class ChooseCommand implements ActionCommand {

	private static final String PARAM_NAME_PARSER = "parser";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String xmlPath = ConfigurationManager.getProperty("path.xml.staff");
		String parserName = request.getParameter(PARAM_NAME_PARSER);
		switch (parserName) {
		case "dom":
			List<Employee> list ;
			DomParser dom = new DomParser();// --Create DOM
			list = dom.domParsing(xmlPath);// Perform parsing. Gain ArrayList with
										// employee
			request.setAttribute("list", list);// put in request's attributes
												// this arraylist
			// System.out.println(dom.domParsing("source/staff.xml"));
			// request.setAttribute("usedParser", parserName);
			// System.out.println(parserName);

			break;
		case "sax":
			Set<Employee> staff;
			StaffSAXBuilder saxBuilder = new StaffSAXBuilder();//--Create SAX
			saxBuilder.buildSetStaff(xmlPath);// Perform parsing.
			staff = saxBuilder.getStaff();//Gain Set with employee
			request.setAttribute("list", staff);// put in request's attributes this Set
			break;
		case "stax":
			Set<Employee> set;
			StaffStAXBuilder staxBuilder = new StaffStAXBuilder();//--Create StAX
			staxBuilder.buildSetStaff(xmlPath);//Perform parsing.
			set = staxBuilder.getStaff();
			request.setAttribute("list", set);// put in request's attributes this Set
			break;

		}
		request.setAttribute("usedParser", parserName);
		page = ConfigurationManager.getProperty("path.page.parserout");// gain
		// target
		// page
		// url
		// from
		// resourse
		// file
		System.out.println(parserName);

		return page;
	}

}
