package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.entity.test.Question;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class EditAnswerQuestionCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(EditAnswerQuestionCommand.class);
	private static final String PARAM_NAME_ID = "questionId";
	// -- not needed
	private static final String PARAM_NAME_QUESTION_CONTENT = "question"
	

	// --------
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Question question = null;

		try {
			int questionId = Integer.parseInt(request
					.getParameter(PARAM_NAME_ID));
			// int testType =
			// Integer.parseInt(request.getParameter(PARAM_NAME_TESTTYPE));
			DaoTest dao = new DaoTest();
			question = dao.showQuestion(questionId);
			page = ConfigurationManager.getProperty("path.page.edit_question");
			request.setAttribute("question", question);
		} catch (TechnicalException | NumberFormatException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;
	}

}
