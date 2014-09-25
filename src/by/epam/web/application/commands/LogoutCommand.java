package by.epam.web.application.commands;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.resource.ConfigurationManager;



public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		// уничтожение сессии
		request.getSession().invalidate();
		return page;
	}

}
