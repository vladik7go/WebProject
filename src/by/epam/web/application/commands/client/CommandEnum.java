package by.epam.web.application.commands.client;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.ChooseCommand;
import by.epam.web.application.commands.LoginCommand;
import by.epam.web.application.commands.LogoutCommand;

public enum CommandEnum {

	LOGIN(new LoginCommand()), 
	CHOOSE(new ChooseCommand()), 
	LOGOUT(new LogoutCommand());

	private final ActionCommand command;// ������ �� ���������� ���� ���������

	private CommandEnum(ActionCommand command) {
		this.command = command;
	}

	// LOGIN {
	// {
	// this.command = new LoginCommand();// ������ �� ���������� ������ ��
	// // ����� ������� execute();
	// }
	// },
	// CHOOSE {
	// {//��������� ����� � ���������� ������
	// this.command = new ChooseCommand();
	// }
	// },
	// LOGOUT {
	// {
	// this.command = new LogoutCommand();
	// }
	// };
	//
	// ActionCommand command; // ������ �� ���������� ���� ���������

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
