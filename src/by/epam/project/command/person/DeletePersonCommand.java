package by.epam.project.command.person;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoPerson;
import by.epam.project.entity.Person;
import by.epam.project.resource.ConfigurationManager;

public class DeletePersonCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(DeletePersonCommand.class);
	private static final String PARAM_NAME_ID = "personId";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		// Extracting parameters from the request
		int personId = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
		DaoPerson dao = new DaoPerson();
		boolean result = dao.deletePerson(personId);
		if (result) {
			page = ConfigurationManager.getProperty("path.page.main_root");
			request.setAttribute("successfullyPerformedAction", "1");
		} else {
			page = ConfigurationManager.getProperty("path.page.login");
			request.setAttribute("successfullyPerformedAction", "0");
		}

		return page;
	}

}
