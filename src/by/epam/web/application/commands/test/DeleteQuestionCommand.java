package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class DeleteQuestionCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(DeleteQuestionCommand.class);
	private static final String PARAM_TEST_ID = "testId";
	private static final String PARAM_NAME_QUESTION_ID = "questionId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));
		int questionId = Integer.parseInt(request
				.getParameter(PARAM_NAME_QUESTION_ID));

		DaoTest dao = new DaoTest();
		boolean result = dao.deleteQuestion(questionId);
		if (result) {
			page = ConfigurationManager.getProperty("path.page.edit_questions");
			request.setAttribute("successfullyPerformedAction", "1");
			request.setAttribute("testId", testId);
			try {
				request.setAttribute("test", dao.showTest(testId));
				request.setAttribute("question", dao.showQuestion(questionId));
			} catch (TechnicalException e) {
				log.error(e);

			}
		} else {
			page = ConfigurationManager.getProperty("path.page.login");
			request.setAttribute("successfullyPerformedAction", "0");
		}

		return page;
	}

}
