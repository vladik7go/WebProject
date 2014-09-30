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
		// извлечение имени команды из запроса
		String action = request.getParameter("command");
		
		if (action == null || action.isEmpty()) {
			// если команда не задана в текущем запросе
			return current;
		}
		// получение объекта, соответствующего команде
		try {
			//заглушка. для дебага. не забыть убрать -----------------------------------------
			log.debug("Command ---- " + action + " ---- had been performed");
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());

			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", "true");
		}
		
		return current;
	}

}
