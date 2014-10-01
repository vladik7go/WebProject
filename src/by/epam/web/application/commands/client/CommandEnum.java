package by.epam.web.application.commands.client;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.login.LoginCommand;
import by.epam.web.application.commands.login.LogoutCommand;
import by.epam.web.application.commands.login.RegistrationCommand;
import by.epam.web.application.commands.person.AddPersonCommand;
import by.epam.web.application.commands.person.ShowPersonsCommand;
import by.epam.web.application.commands.person.EditPersonCommand;
import by.epam.web.application.commands.person.EditWritePersonCommand;
import by.epam.web.application.commands.person.DeletePerson;

public enum CommandEnum {

	LOGIN(new LoginCommand()),
	REGISTRATION(new RegistrationCommand()), 
	ADDPERSON(new AddPersonCommand()), 
	DELETEPERSON(new DeletePerson()),
	EDITPERSON(new EditPersonCommand()),
	EDITWRITEPERSON(new EditWritePersonCommand()),
	SHOWPERSONS(new ShowPersonsCommand()),
	LOGOUT(	new LogoutCommand());

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
