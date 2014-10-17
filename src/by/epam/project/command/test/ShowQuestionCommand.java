package by.epam.project.command.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoTest;
import by.epam.project.entity.test.Question;
import by.epam.project.exception.TechnicalException;
import by.epam.project.resource.ConfigurationManager;

public class ShowQuestionCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(ShowQuestionCommand.class);
	private static final String PARAM_NAME_ID = "questionId";
	private static final String PARAM_TEST_ID = "testId";
	
	private static final String PARAM_NAME_QUESTION_CONTENT = "question";
	

	
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Question question = null;

		try {
			int questionId = Integer.parseInt(request
					.getParameter(PARAM_NAME_ID));
			int testId = Integer.parseInt(request
					.getParameter(PARAM_TEST_ID));
			DaoTest dao = new DaoTest();
			question = dao.showQuestion(questionId);
			page = ConfigurationManager.getProperty("path.page.edit_question");
			request.setAttribute("question", question);
			request.setAttribute("testId", testId);
		} catch (TechnicalException | NumberFormatException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;
	}

}
