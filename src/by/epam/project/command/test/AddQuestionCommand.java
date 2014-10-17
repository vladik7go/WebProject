package by.epam.project.command.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoTest;
import by.epam.project.exception.TechnicalException;
import by.epam.project.resource.ConfigurationManager;

public class AddQuestionCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(AddQuestionCommand.class);
	private static final String PARAM_NAME_TEST_ID = "testId";
	private static final String PARAM_NAME_QUESTION_CONTENT = "questionContent";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int testId = Integer.parseInt(request.getParameter(PARAM_NAME_TEST_ID));
		String questionContent = request.getParameter(
				PARAM_NAME_QUESTION_CONTENT).trim();
		// Checking for empty fields
		DaoTest dao = new DaoTest();
		if (questionContent.length() * testId != 0) {
			boolean result = dao.addQuestion(testId, questionContent);
			if (result) {
				page = ConfigurationManager
						.getProperty("path.page.edit_questions");
				request.setAttribute("successfullyPerformedAction", "1");
				try {
					request.setAttribute("test", dao.showTest(testId));
				} catch (TechnicalException e) {
					log.error(e);
				}
			} else {
				page = ConfigurationManager.getProperty("path.page.main_tutor");
				request.setAttribute("successfullyPerformedAction", "0");
			}

		} else {
			request.setAttribute("errorEmptyFieldMessage", "true");
			try {
				request.setAttribute("test", dao.showTest(testId));

			} catch (TechnicalException e) {
				log.error(e);
			}
			page = ConfigurationManager.getProperty("path.page.edit_questions");
		}

		return page;
	}

}
