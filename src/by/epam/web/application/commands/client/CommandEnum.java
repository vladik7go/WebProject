package by.epam.web.application.commands.client;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.AddPersonCommand;
import by.epam.web.application.commands.ChooseCommand;

import by.epam.web.application.commands.LoginCommand;
import by.epam.web.application.commands.LogoutCommand;
import by.epam.web.application.commands.RegistrationCommand;

public enum CommandEnum {

	LOGIN(new LoginCommand()), 
	
	REGISTRATION(new RegistrationCommand()),
	ADDPERSON(new AddPersonCommand()),
	CHOOSE(new ChooseCommand()), 
	LOGOUT(new LogoutCommand());

	private final ActionCommand command;// ссылка на переменную типа интерфейс

	private CommandEnum(ActionCommand command) {
		this.command = command;
	}

	// LOGIN {
	// {
	// this.command = new LoginCommand();// —сылка на конкретный объект со
	// // своим методом execute();
	// }
	// },
	// CHOOSE {
	// {//јнонимный класс с логическим блоком
	// this.command = new ChooseCommand();
	// }
	// },
	// LOGOUT {
	// {
	// this.command = new LogoutCommand();
	// }
	// };
	//
	// ActionCommand command; // ссылка на переменную типа интерфейс

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
