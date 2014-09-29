package by.epam.web.application.commands.login;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		/*
		 * � ������ ������ ��� ������� ��������� � ����������� ������������� ��
		 * �������� ����� ������
		 */
		String page = ConfigurationManager.getProperty("path.page.login");
		// ��������� � ������� �������� � ������ "language" � ���������
		// ��������� � ������� �� ������ ������� ��������� "language".
		// ����������
		// ���� ������ ������ ������
		// ����� � login.jsp � �������� ��������� - ������.

		request.setAttribute("language", request.getParameter("language"));
		return page;
	}

}
