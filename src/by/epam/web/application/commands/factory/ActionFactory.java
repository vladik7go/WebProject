package by.epam.web.application.commands.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.client.CommandEnum;
import by.epam.web.application.commands.login.EmptyCommand;
import by.epam.web.application.controller.Controller;
import by.epam.web.application.resource.MessageManager;

public class ActionFactory {
	public static Logger log = Logger.getLogger(ActionFactory.class);

	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		
		//Extracting the name of the command from request
		
		String action = request.getParameter("command");
		
		if (action == null || action.isEmpty()) {
			
			//if command do not defined in current request
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
