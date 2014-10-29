package by.epam.project.command.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.command.client.CommandEnum;
import by.epam.project.command.login.EmptyCommand;
import by.epam.project.controller.Controller;
import by.epam.project.resource.MessageManager;

public class ActionFactory {
	public static Logger log = Logger.getLogger(ActionFactory.class);

	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();

		// Extracting the name of the command from request

		String action = request.getParameter("command");
		String role = (String) request.getSession().getAttribute("role");
		log.debug("role: " + role);
		log.debug("action: " + action);

		// if command do not defined in current request
		if (action == null || action.isEmpty()) {
			log.debug("Command ---- EmptyCommand ---- had been performed");
			return current;
		}

		/*
		 * This IF-block prevents direct access to ANY command, except Login,
		 * Registration and Add_person. So, if a hacker would be able to
		 * substitute request, anyhow - he would be redirected to login page.
		 */
		if ((role == null || role.isEmpty())
				&& (!action.equals("login") && !action.equals("registration") && !action
						.equals("add_person"))) {
			log.info("attempt to hack");
			log.debug("Command ---- EmptyCommand ---- had been performed");
			return current;
		}

		// gaining the object, according to the command
		try {

			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());

			current = currentEnum.getCurrentCommand();
			log.debug("Command ---- " + action + " ---- had been performed");
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", "true");
		}

		return current;
	}

}
