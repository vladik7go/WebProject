package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class EditWriteQuestionCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(EditWriteQuestionCommand.class);
	private static final String PARAM_TEST_ID = "testId";
	private static final String PARAM_NAME_QUESTION_ID = "questionId";
	private static final String PARAM_NAME_QUESTION_CONTENT = "questionContent";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String questionContent = request
				.getParameter(PARAM_NAME_QUESTION_CONTENT).trim();
		int questionId = Integer.parseInt(request
				.getParameter(PARAM_NAME_QUESTION_ID));
		int testId = Integer.parseInt(request
				.getParameter(PARAM_TEST_ID));
		// Checking for empty fields
		DaoTest dao = new DaoTest();
		if (questionContent.length() * questionId != 0) {
			boolean result = dao.editQuestion(questionId, questionContent);
			if (result) {
				page = ConfigurationManager
						.getProperty("path.page.edit_question");
				request.setAttribute("successfullyPerformedAction", "1");
				request.setAttribute("testId", testId);
				try {
					request.setAttribute("question",
							dao.showQuestion(questionId));
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
				request.setAttribute("testId", testId);
				request.setAttribute("question", dao.showQuestion(questionId));
			} catch (TechnicalException e) {
				log.error(e);
			}
			page = ConfigurationManager.getProperty("path.page.edit_question");
		}

		return page;
	}

}
