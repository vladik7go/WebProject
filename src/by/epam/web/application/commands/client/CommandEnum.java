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
import by.epam.web.application.commands.test.AddQuestionCommand;
import by.epam.web.application.commands.test.AddTestCommand;
import by.epam.web.application.commands.test.DeleteAnswerCommand;
import by.epam.web.application.commands.test.DeleteQuestionCommand;
import by.epam.web.application.commands.test.DeleteTestCommand;
import by.epam.web.application.commands.test.EditWriteAnswerCommand;
import by.epam.web.application.commands.test.EditWriteQuestionCommand;
import by.epam.web.application.commands.test.PerformTestCommand;
import by.epam.web.application.commands.test.ShowQuestionCommand;
import by.epam.web.application.commands.test.EditQuestionsCommand;
import by.epam.web.application.commands.test.EditTestCommand;
import by.epam.web.application.commands.test.EditWriteTestCommand;
import by.epam.web.application.commands.test.ShowResultCommand;
import by.epam.web.application.commands.test.ShowTestsCommand;
import by.epam.web.application.commands.test.TempCommand;


public enum CommandEnum {

	LOGIN(new LoginCommand()),
	REGISTRATION(new RegistrationCommand()), 
	ADDPERSON(new AddPersonCommand()), 
	DELETEPERSON(new DeletePersonCommand()),
	EDITPERSON(new EditPersonCommand()),
	EDITWRITEPERSON(new EditWritePersonCommand()),
	SHOWPERSONS(new ShowPersonsCommand()),
	PERFORMTEST(new PerformTestCommand()),
	ADDTEST(new AddTestCommand()),
	SHOWTESTS(new ShowTestsCommand()),
	EDITTEST(new EditTestCommand()),
	EDITWRITETEST(new EditWriteTestCommand()),
	DELETETEST(new DeleteTestCommand()),
	ADDQUESTION(new AddQuestionCommand()),
	EDITQUESTIONS(new EditQuestionsCommand()),
	EDITQUESTION(new ShowQuestionCommand()),
	EDITWRITEQUESTION(new EditWriteQuestionCommand()),
	DELETEQUESTION(new DeleteQuestionCommand()),
	ADDANSWER(new AddAnswerCommand()),
	EDITWRITEANSWER(new EditWriteAnswerCommand()),
	DELETEANSWER(new DeleteAnswerCommand()),
	SHOWRESULT(new ShowResultCommand()),
	LOGOUT(	new LogoutCommand()),
	TEMPCOMMAND(new TempCommand());

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
