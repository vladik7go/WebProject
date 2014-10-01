package by.epam.web.application.commands.person;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoPerson;
import by.epam.web.application.entity.Person;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class ShowPersonsCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(ShowPersonsCommand.class);
	
	
	@Override
	public String execute(HttpServletRequest request) {
//		System.out.println("showPersonsCommand execute method");
		List<Person> persons = null;
		String page = null;
		DaoPerson dao = new DaoPerson();
		try {
			
			persons = dao.showPersons();
			page = ConfigurationManager.getProperty("path.page.main_root");
			request.setAttribute("personsList", persons);
		} catch (TechnicalException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}
		
		
		
		return page;
	}

}
