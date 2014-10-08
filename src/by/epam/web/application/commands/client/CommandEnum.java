package by.epam.web.application.commands.client;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.login.LoginCommand;
import by.epam.web.application.commands.login.LogoutCommand;
import by.epam.web.application.commands.login.RegistrationCommand;
import by.epam.web.application.commands.person.AddPersonCommand;
import by.epam.web.application.commands.person.DeletePersonCommand;
import by.epam.web.application.commands.person.ShowPersonsCommand;
import by.epam.web.application.commands.person.EditPersonCommand;
import by.epam.web.application.commands.person.EditWritePersonCommand;
import by.epam.web.application.commands.test.AddAnswerCommand;
import by.epam.web.application.commands.test.DeleteAnswerCommand;
import by.epam.web.application.commands.test.EditWriteAnswerCommand;
import by.epam.web.application.commands.test.EditWriteQuestionCommand;
import by.epam.web.application.commands.test.ShowQuestionCommand;
import by.epam.web.application.commands.test.EditQuestionsCommand;
import by.epam.web.application.commands.test.EditTestCommand;
import by.epam.web.application.commands.test.EditWriteTestCommand;
import by.epam.web.application.commands.test.ShowTestsCommand;


public enum CommandEnum {

	LOGIN(new LoginCommand()),
	REGISTRATION(new RegistrationCommand()), 
	ADDPERSON(new AddPersonCommand()), 
	DELETEPERSON(new DeletePersonCommand()),
	EDITPERSON(new EditPersonCommand()),
	EDITWRITEPERSON(new EditWritePersonCommand()),
	SHOWPERSONS(new ShowPersonsCommand()),
	SHOWTESTS(new ShowTestsCommand()),
	EDITTEST(new EditTestCommand()),
	EDITWRITETEST(new EditWriteTestCommand()),
	EDITQUESTIONS(new EditQuestionsCommand()),
	EDITQUESTION(new ShowQuestionCommand()),
	EDITWRITEQUESTION(new EditWriteQuestionCommand()),
	ADDANSWER(new AddAnswerCommand()),
	EDITWRITEANSWER(new EditWriteAnswerCommand()),
	DELETEANSWER(new DeleteAnswerCommand()),
	LOGOUT(	new LogoutCommand());

	private final ActionCommand command;// Reference on the variable with type - interface 

	private CommandEnum(ActionCommand command) {
		this.command = command;
	}

	// LOGIN {
	// {
	// this.command = new LoginCommand();// Reference to the concrete object 
	// // with his own method execute();
	// }
	// },
	// CHOOSE {
	// {// Anonymous class with logical block included  
	// this.command = new ChooseCommand();
	// }
	// },
	// LOGOUT {
	// {
	// this.command = new LogoutCommand();
	// }
	// };
	//
	// ActionCommand command; // Reference on the variable with type - interface 

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
