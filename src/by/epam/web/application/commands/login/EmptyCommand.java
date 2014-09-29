package by.epam.web.application.commands.login;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		/*
		 * в случае ошибки или прямого обращения к контроллеру переадресация на
		 * страницу ввода логина
		 */
		String page = ConfigurationManager.getProperty("path.page.login");
		// добавляем к запросу аттрибут с именем "language" и значением
		// пришедшим в запросе во втором скрытом параметре "language".
		// Переменная
		// была задана формой выбора
		// языка в login.jsp с областью видимости - сессия.

		request.setAttribute("language", request.getParameter("language"));
		return page;
	}

}
