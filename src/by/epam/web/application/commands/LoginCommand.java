package by.epam.web.application.commands;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.logic.LoginLogic;
import by.epam.web.application.resource.ConfigurationManager;
import by.epam.web.application.resource.MessageManager;



public class LoginCommand implements ActionCommand {

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		// извлечение из запроса логина и пароля
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		// проверка логина и пароля
		LoginLogic logic = new LoginLogic();
		if (logic.checkLogin(login, pass)) {
			request.setAttribute("user", login);
			// определение пути к main.jsp
			page = ConfigurationManager.getProperty("path.page.main");
		} else {
			request.setAttribute("errorLoginPassMessage",
					MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}
		return page;
	}

}
