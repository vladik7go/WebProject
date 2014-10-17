package by.epam.project.command.login;

import javax.servlet.http.HttpServletRequest;

import by.epam.project.command.ActionCommand;
import by.epam.project.resource.ConfigurationManager;



public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		//destroy the session
		request.getSession().invalidate();
		return page;
	}

}
