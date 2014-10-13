package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoPerson;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class TempCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(TempCommand.class);
	private static final String PARAM_NAME_ANSWER_VARIANT = "answerVariant";
	private static final String PARAM_TEST_ID = "testId";
	private static final String PARAM_NAME_QUESTION_ID = "questionId";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		
		String[] answerValues=request.getParameterValues(PARAM_NAME_ANSWER_VARIANT);
		DaoTest dao = new DaoTest();
		DaoPerson daoPerson = new DaoPerson();
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));
		
		try {
			for (int i = 0; answerValues.length > i; i++) {
				log.debug("value " + i + " = " + answerValues[i]);
			}
		} catch (NullPointerException e) {
			log.error("the checkbox is empty", e);
			request.setAttribute("errorEmptyFieldMessage", "true");
			try {
				request.setAttribute("question", dao.showQuestion(22));
				request.setAttribute("test", dao.showTest(testId));
			} catch (TechnicalException e1) {
				log.error(e1);
			}
			page = ConfigurationManager.getProperty("path.page.perform_test");
		}

		
		return page;
	}

}
