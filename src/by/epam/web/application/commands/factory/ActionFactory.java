package by.epam.web.application.commands.factory;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.client.CommandEnum;
import by.epam.web.application.commands.login.EmptyCommand;
import by.epam.web.application.resource.MessageManager;

public class ActionFactory {

	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		// ���������� ����� ������� �� �������
		String action = request.getParameter("command");
		//��������. ��� ������. �� ������ ������ -----------------------------------------
		System.out.println("----" + action);
		if (action == null || action.isEmpty()) {
			// ���� ������� �� ������ � ������� �������
			return current;
		}
		// ��������� �������, ���������������� �������
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());

			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", "true");
		}
		
		return current;
	}

}
