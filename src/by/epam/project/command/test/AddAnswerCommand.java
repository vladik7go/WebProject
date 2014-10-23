package by.epam.project.command.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoTest;
import by.epam.project.exception.TechnicalException;
import by.epam.project.resource.ConfigurationManager;

public class AddAnswerCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(AddAnswerCommand.class);
	private static final String PARAM_NAME_TEST_ID = "testId";
	private static final String PARAM_NAME_QUESTION_ID = "questionId";
	private static final String PARAM_NAME_ANSWER_CONTENT = "answerContent";
	private static final String PARAM_NAME_ANSWER_VALUE = "answerValue";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int testId = Integer.parseInt(request.getParameter(PARAM_NAME_TEST_ID));
		int questionId = Integer.parseInt(request
				.getParameter(PARAM_NAME_QUESTION_ID));
		int answerValue;

		answerValue = Integer.parseInt(request.getParameter(
				PARAM_NAME_ANSWER_VALUE).trim());

		String answerContent = request.getParameter(PARAM_NAME_ANSWER_CONTENT).trim();
		// Checking for empty fields
		DaoTest dao = new DaoTest();
		if (answerContent.length() * questionId != 0) {
			boolean result = dao.addAnswer(questionId, answerContent,
					answerValue);
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
