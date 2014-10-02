package by.epam.web.application.commands.login;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		/*
		 * 
		 * In case of error or direct request to the controller - readdressing
		 * on the login page
		 */
		String page = ConfigurationManager.getProperty("path.page.login");

		/*
		 * adding to the request: adding the attribute with name "language" and
		 * value taken from request from second hidden parameter "language".
		 * Variable was defined by the form of choose language in login.jsp
		 * with area of visibility - session.
		 */

		request.setAttribute("language", request.getParameter("language"));
		return page;
	}

}
