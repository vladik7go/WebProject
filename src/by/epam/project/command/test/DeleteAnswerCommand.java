package by.epam.project.command.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.command.person.DeletePersonCommand;
import by.epam.project.dao.DaoTest;
import by.epam.project.exception.TechnicalException;
import by.epam.project.resource.ConfigurationManager;

public class DeleteAnswerCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(DeleteAnswerCommand.class);
	private static final String PARAM_TEST_ID = "testId";
	private static final String PARAM_NAME_ID = "answerId";
	private static final String PARAM_NAME_QUESTION_ID = "questionId";

	@Override
	public String execute(HttpServletRequest request) {
		log.debug("in attribute question lies = "
				+ request.getAttribute("question"));
		String page = null;
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));
		int answerId = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
		int questionId = Integer.parseInt(request
				.getParameter(PARAM_NAME_QUESTION_ID));
		DaoTest dao = new DaoTest();
		boolean result = dao.deleteAnswer(answerId);

		if (result) {
			page = ConfigurationManager.getProperty("path.page.edit_question");
			request.setAttribute("successfullyPerformedAction", "1");
			request.setAttribute("testId", testId);
			try {
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
