package by.epam.project.command.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.Session;
import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.command.test.PerformTestCommand;
import by.epam.project.command.test.ShowResultCommand;
import by.epam.project.command.test.ShowTestsCommand;
import by.epam.project.dao.DaoPerson;
import by.epam.project.dao.DaoTest;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.LoginLogic;
import by.epam.project.resource.ConfigurationManager;
import by.epam.project.resource.MessageManager;

/*
 * This command: extracting login and password from request.
 * Trying to extract ID of the user with this login and password from the table "person".
 * This command return address of the page(to which request should be redirected ) according to ID of the user.
 * In case, if respective login and password doesn't exist in the table - return link to the login page again.
 */
public class LoginCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(LoginCommand.class);

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
		DaoPerson dao = new DaoPerson();

		switch (logic.checkLogin(login, pass)) {
		case 3:
			page = ConfigurationManager.getProperty("path.page.main_student");
			request.getSession().setAttribute("role", "student");
			try {

				String passMd5 = logic.getHash(pass);
				String personId = String.valueOf(dao.showPerson(login, passMd5)
						.getId());
				request.getSession().setAttribute("personId", personId);
			} catch (TechnicalException e1) {
				log.error("login command: ", e1);
			}
			// In order to place in the request - "testsList" attribute
			// (ArrayList object)
			ShowTestsCommand showTestsCommand = new ShowTestsCommand();
			showTestsCommand.execute(request);
			// In order to place in the request - "result" attribute (HashMap
			// object)
			ShowResultCommand showResult = new ShowResultCommand();
			showResult.execute(request);

			break;
		case 2:
			page = ConfigurationManager.getProperty("path.page.main_tutor");
			request.getSession().setAttribute("role", "tutor");

			break;
		case 1:
			page = ConfigurationManager.getProperty("path.page.main_root");
			request.getSession().setAttribute("role", "root");

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
		try {

			String passMd5 = logic.getHash(pass);
			request.setAttribute("person", dao.showPerson(login, passMd5));
			request.getSession().setAttribute("personId",
					String.valueOf(dao.showPerson(login, passMd5).getId()));
		} catch (TechnicalException e) {
			log.error(e);
		}
		return page;
	}
}
