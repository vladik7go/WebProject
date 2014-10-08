package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;

public class AddAnswerCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(AddAnswerCommand.class);
	private static final String PARAM_NAME_TEST_ID = "testId";
	private static final String PARAM_NAME_QUESTION_ID = "questionId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int testId = Integer.parseInt(request
				.getParameter(PARAM_NAME_TEST_ID));
		int questionId = Integer.parseInt(request
				.getParameter(PARAM_NAME_QUESTION_ID));
		
		
		
		return page;
	}

}
