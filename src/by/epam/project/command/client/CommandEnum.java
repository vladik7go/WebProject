package by.epam.project.command.client;

import by.epam.project.command.ActionCommand;
import by.epam.project.command.login.LoginCommand;
import by.epam.project.command.login.LogoutCommand;
import by.epam.project.command.login.RegistrationCommand;
import by.epam.project.command.person.AddPersonCommand;
import by.epam.project.command.person.DeletePersonCommand;
import by.epam.project.command.person.EditPersonCommand;
import by.epam.project.command.person.EditWritePersonCommand;
import by.epam.project.command.person.ShowPersonsCommand;
import by.epam.project.command.person.ShowResultsCommand;
import by.epam.project.command.test.AddAnswerCommand;
import by.epam.project.command.test.AddQuestionCommand;
import by.epam.project.command.test.AddTestCommand;
import by.epam.project.command.test.DeleteAnswerCommand;
import by.epam.project.command.test.DeleteQuestionCommand;
import by.epam.project.command.test.DeleteTestCommand;
import by.epam.project.command.test.EditQuestionsCommand;
import by.epam.project.command.test.EditTestCommand;
import by.epam.project.command.test.EditWriteAnswerCommand;
import by.epam.project.command.test.EditWriteQuestionCommand;
import by.epam.project.command.test.EditWriteTestCommand;
import by.epam.project.command.test.PerformTestCommand;
import by.epam.project.command.test.PerformWriteTestCommand;
import by.epam.project.command.test.ShowQuestionCommand;
import by.epam.project.command.test.ShowResultCommand;
import by.epam.project.command.test.ShowTestsCommand;
import by.epam.project.command.test.WriteResultCommand;


public enum CommandEnum {

	LOGIN(new LoginCommand()),
	REGISTRATION(new RegistrationCommand()), 
	ADDPERSON(new AddPersonCommand()), 
	DELETEPERSON(new DeletePersonCommand()),
	EDITPERSON(new EditPersonCommand()),
	EDITWRITEPERSON(new EditWritePersonCommand()),
	SHOWPERSONS(new ShowPersonsCommand()),
	PERFORMTEST(new PerformTestCommand()),
	PERFORMWRITETEST(new PerformWriteTestCommand()),
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
	SHOWRESULTS(new ShowResultsCommand()),
	WRITERESULT(new WriteResultCommand()),
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
