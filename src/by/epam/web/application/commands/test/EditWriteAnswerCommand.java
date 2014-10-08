package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class EditWriteAnswerCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(EditWriteAnswerCommand.class);
	private static final String PARAM_TEST_ID = "testId";
	private static final String PARAM_NAME_ANSWER_ID = "answerId";
	private static final String PARAM_NAME_QUESTION_ID = "questionId";
	private static final String PARAM_NAME_ANSWER_CONTENT = "answerContent";
	private static final String PARAM_NAME_ANSWER_VALUE = "answerValue";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;

		String answerContent = request.getParameter(PARAM_NAME_ANSWER_CONTENT);
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));

		int answerId = Integer.parseInt(request.getParameter(
				PARAM_NAME_ANSWER_ID).trim());
		int questionId = Integer.parseInt(request
				.getParameter(PARAM_NAME_QUESTION_ID));
		int answerValue = Integer.parseInt(request.getParameter(
				PARAM_NAME_ANSWER_VALUE).trim());

		// Checking for empty fields
		DaoTest dao = new DaoTest();
		if (answerContent.length() * answerId != 0) {
			log.debug("parameters, received by command 'EditWriteAnswer' = "
					+ answerId + " : " + answerValue + " : " + answerContent);
			boolean result = dao.editAnswer(answerId, answerValue,
					answerContent);
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
				request.setAttribute("question", dao.showQuestion(questionId));
			} catch (TechnicalException e) {
				log.error(e);
			}
			page = ConfigurationManager.getProperty("path.page.edit_question");
		}

		return page;
	}

}
