package by.epam.web.application.commands.login;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.logic.LoginLogic;
import by.epam.web.application.resource.ConfigurationManager;
import by.epam.web.application.resource.MessageManager;

public class LoginCommand implements ActionCommand {

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		// ���������� �� ������� ������ � ������
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		// �������� ������ � ������
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
			// ��������� � ������� �������� � ������ "language" � ���������
			// ��������� � ������� �� ������ ������� ��������� "language".
			// ����������
			// ���� ������ ������ ������
			// ����� � login.jsp � �������� ��������� - ������.

			request.setAttribute("language", request.getParameter("language"));

			request.setAttribute("errorLoginPassMessage", "true");
			page = ConfigurationManager.getProperty("path.page.login");
		}

		// if (logic.checkLogin(login, pass) !=0) {
		//
		// request.setAttribute("user", login);
		// // ����������� ���� � main.jsp
		//
		// page = ConfigurationManager.getProperty("path.page.main_student");
		// } else {
		// // ��������� � ������� �������� � ������ "language" � ���������
		// // ��������� � ������� �� ������ ������� ��������� "language".
		// // ����������
		// // ���� ������ ������ ������
		// // ����� � login.jsp � �������� ��������� - ������.
		//
		// request.setAttribute("language", request.getParameter("language"));
		//
		// request.setAttribute("errorLoginPassMessage", "true");
		// page = ConfigurationManager.getProperty("path.page.login");
		// }
		return page;
	}
}
