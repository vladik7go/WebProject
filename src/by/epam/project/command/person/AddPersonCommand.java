package by.epam.project.command.person;

import java.awt.SecondaryLoop;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.controller.Controller;
import by.epam.project.dao.DaoPerson;
import by.epam.project.logic.LoginLogic;
import by.epam.project.resource.ConfigurationManager;
import by.epam.project.resource.MessageManager;

public class AddPersonCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(AddPersonCommand.class);
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_FIRST_NAME = "firstName";
	private static final String PARAM_NAME_SECOND_NAME = "secondName";
	private static final String PARAM_NAME_ROLE = "role";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		// Extracting parameters from the request
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		String firstName = request.getParameter(PARAM_NAME_FIRST_NAME);
		String secondName = request.getParameter(PARAM_NAME_SECOND_NAME);
		int role = Integer.parseInt(request.getParameter(PARAM_NAME_ROLE));

		if (firstName.length() * secondName.length() * login.length()
				* pass.length() == 0) {

			request.setAttribute("errorEmptyFieldMessage", "true");
			page = ConfigurationManager.getProperty("path.page.registration");
		} else {
			DaoPerson dao = new DaoPerson();
			LoginLogic logic = new LoginLogic();
			pass = logic.getHash(pass);
			boolean result = dao.addPerson(firstName, secondName, login, pass,
					role);
			page = ConfigurationManager.getProperty("path.page.login");
			if (!result) {
				request.setAttribute("errorDuplicateEntryMessage", "true");
				page = ConfigurationManager
						.getProperty("path.page.registration");
			}
			// Return string with the address of the login page

		}

		return page;
	}
}
