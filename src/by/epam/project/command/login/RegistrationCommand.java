package by.epam.project.command.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.resource.ConfigurationManager;

public class RegistrationCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(RegistrationCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		page = ConfigurationManager.getProperty("path.page.registration");

		return page;
	}

}
