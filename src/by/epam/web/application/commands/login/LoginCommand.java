package by.epam.web.application.commands.login;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.logic.LoginLogic;
import by.epam.web.application.resource.ConfigurationManager;
import by.epam.web.application.resource.MessageManager;

/*
 * This command: extracting login and password from request.
 * Trying to extract ID of the user with this login and password from the table "person".
 * This command return address of the page(to which request should be redirected ) according to ID of the user.
 * In case, if respective login and password doesn't exist in the table - return link to the login page again.
 */
public class LoginCommand implements ActionCommand {

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;

		// Extracting from the request login and password
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		
		// check the login and the password
		LoginLogic logic = new LoginLogic();

		switch (logic.checkLogin(login, pass)) {
		case 3:
			page = ConfigurationManager.getProperty("path.page.main_student");
			request.setAttribute("user", login);
			break;
		case 2:
			page = ConfigurationManager.getProperty("path.page.main_tutor");
			request.setAttribute("user", login);
			break;
		case 1:
			page = ConfigurationManager.getProperty("path.page.main_root");
			request.setAttribute("user", login);
			break;
		default:

			/*
			 * adding to the request: adding the attribute with name "language"
			 * and value taken from request from second hidden parameter
			 * "language". Variable was defined by the form of choose language
			 * in login.jsp with area of visibility - session.
			 */

			request.setAttribute("language", request.getParameter("language"));

			request.setAttribute("errorLoginPassMessage", "true");
			page = ConfigurationManager.getProperty("path.page.login");
		}

		
		return page;
	}
}
