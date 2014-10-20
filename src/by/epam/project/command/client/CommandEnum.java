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
	ADD_PERSON(new AddPersonCommand()), 
	DELETE_PERSON(new DeletePersonCommand()),
	EDIT_PERSON(new EditPersonCommand()),
	EDIT_WRITE_PERSON(new EditWritePersonCommand()),
	SHOW_PERSONS(new ShowPersonsCommand()),
	PERFORM_TEST(new PerformTestCommand()),
	PERFORM_WRITE_TEST(new PerformWriteTestCommand()),
	ADD_TEST(new AddTestCommand()),
	SHOW_TESTS(new ShowTestsCommand()),
	EDIT_TEST(new EditTestCommand()),
	EDIT_WRITE_TEST(new EditWriteTestCommand()),
	DELETE_TEST(new DeleteTestCommand()),
	ADD_QUESTION(new AddQuestionCommand()),
	EDIT_QUESTIONS(new EditQuestionsCommand()),
	EDIT_QUESTION(new ShowQuestionCommand()),
	EDIT_WRITE_QUESTION(new EditWriteQuestionCommand()),
	DELETE_QUESTION(new DeleteQuestionCommand()),
	ADD_ANSWER(new AddAnswerCommand()),
	EDIT_WRITE_ANSWER(new EditWriteAnswerCommand()),
	DELETE_ANSWER(new DeleteAnswerCommand()),
	SHOW_RESULT(new ShowResultCommand()),
	SHOW_RESULTS(new ShowResultsCommand()),
	WRITE_RESULT(new WriteResultCommand()),
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
