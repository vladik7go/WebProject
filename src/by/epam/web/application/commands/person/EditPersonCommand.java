package by.epam.web.application.commands.person;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoPerson;
import by.epam.web.application.entity.Person;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class EditPersonCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(EditPersonCommand.class);
	private static final String PARAM_NAME_ID = "personId";

	@Override
	public String execute(HttpServletRequest request) {
		Person person = null;
		String page = null;
		// Extracting parameters from the request

		try {
		int personId = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
		DaoPerson dao = new DaoPerson();
			person = dao.showPerson(personId);
			String role =(String)request.getSession().getAttribute("role");
			switch (role){
			case "student":
				page = ConfigurationManager.getProperty("path.page.main_student");
				break;
			case "tutor":
				page = ConfigurationManager.getProperty("path.page.main_tutor");
				break;
			case "root":
				page = ConfigurationManager.getProperty("path.page.edit_user");
				break;
			}
			
			request.setAttribute("person", person);
			
		} catch (TechnicalException | NumberFormatException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}
		

		return page;
	}

}
