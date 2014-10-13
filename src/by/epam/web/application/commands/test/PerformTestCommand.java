package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoPerson;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.entity.test.Question;
import by.epam.web.application.entity.test.Test;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.logic.TestLogic;
import by.epam.web.application.resource.ConfigurationManager;

public class PerformTestCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(PerformTestCommand.class);
	private static final String PARAM_TEST_ID = "testId";
	private static final String PARAM_NAME_QUESTION_ID = "questionId";
	private static final String PARAM_NAME_ID = "personId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Test test = null;
		Question question = null;
		int personId = Integer.parseInt(((String) request.getSession()
				.getAttribute(PARAM_NAME_ID)).trim());
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));
//		int questionId = Integer.parseInt(request.getParameter(PARAM_NAME_QUESTION_ID));

		DaoTest dao = new DaoTest();
		DaoPerson daoPerson = new DaoPerson();
		TestLogic logic = new TestLogic();
		try {
			test = dao.showTest(testId);
			page = ConfigurationManager.getProperty("path.page.perform_test");
			request.setAttribute("test", test);
			request.setAttribute("person", daoPerson.showPerson(personId));
			
			
			request.setAttribute("question", dao.showQuestion(22));
			
		} catch (TechnicalException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;
	}

}
