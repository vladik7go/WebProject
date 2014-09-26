package by.epam.web.application.commands;

import java.awt.SecondaryLoop;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.controller.Controller;
import by.epam.web.application.dao.DaoPerson;
import by.epam.web.application.resource.ConfigurationManager;
import by.epam.web.application.resource.MessageManager;

public class AddPersonCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(AddPersonCommand.class);
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_FIRST_NAME = "firstName";
	private static final String PARAM_NAME_SECOND_NAME = "secondName";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		// извлечение из запроса логина и пароля
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		String firstName = request.getParameter(PARAM_NAME_FIRST_NAME);
		String secondName = request.getParameter(PARAM_NAME_SECOND_NAME);
		
		if (firstName == "" || secondName == "" || login == "" || pass == "") {

			request.setAttribute("errorEmptyFieldMessage",
					MessageManager.getProperty("message.emptyfielderror"));
			page = ConfigurationManager.getProperty("path.page.registration");
		} else {
			DaoPerson dao = new DaoPerson();

			dao.addPerson(firstName, secondName, login, pass);
			

			page = ConfigurationManager.getProperty("path.page.main");// возвращаем
																		// строку
																		// с
																		// адресом
																		// главной
																		// страницы
		}

		return page;
	}
}
