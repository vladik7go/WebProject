package by.epam.project.command.person;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoPerson;
import by.epam.project.entity.person.Person;
import by.epam.project.exception.TechnicalException;
import by.epam.project.resource.ConfigurationManager;

public class ShowPersonsCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(ShowPersonsCommand.class);

	@Override
	public String execute(HttpServletRequest request) {

		List<Person> persons = null;
		String page = null;
		DaoPerson dao = new DaoPerson();
		try {

			persons = dao.showPersons();
			page = ConfigurationManager.getProperty("path.page.main_root");
			request.setAttribute("personsList", persons);
			request.setAttribute("successfullyPerformedAction",
					request.getParameter("successfullyPerformedAction"));
			request.setAttribute("errorEmptyResultTableMessage",
					request.getParameter("errorEmptyResultTableMessage"));
		} catch (TechnicalException e) {
			log.error("ShowPersonsCommand: ", e);
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;
	}

}
