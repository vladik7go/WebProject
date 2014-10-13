package by.epam.web.application.commands.test;

import java.util.ArrayList;
import java.util.Set;

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
	private static final String PARAM_NAME_QUESTIONS_ID_LIST = "questionsIdList";
	private static final String PARAM_NAME_ID = "personId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Test test = null;
		Question question = null;
		int personId = Integer.parseInt(((String) request.getSession()
				.getAttribute(PARAM_NAME_ID)).trim());
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));
		int questionId = 0;
		// int questionId =
		// Integer.parseInt(request.getParameter(PARAM_NAME_QUESTION_ID));

		DaoTest dao = new DaoTest();
		DaoPerson daoPerson = new DaoPerson();
		TestLogic logic = new TestLogic();
		Set<Question> questions = null;
		ArrayList<Integer> questionsIdList = new ArrayList<>();

		try {

			if (request.getParameter(PARAM_NAME_QUESTION_ID) == null) {

				test = dao.showTest(testId);
				questions = test.getQuestions();

				for (Question elem : questions) {
					questionsIdList.add(elem.getId());

				}
				questionId = questionsIdList.get(0);
			} else {
				questionId = Integer.parseInt(request
						.getParameter(PARAM_NAME_QUESTION_ID));
			}
			// ----------------
			page = ConfigurationManager.getProperty("path.page.perform_test");
			request.setAttribute("test", test);
			request.setAttribute("person", daoPerson.showPerson(personId));

			request.setAttribute("question", dao.showQuestion(questionId));
			request.setAttribute("questionsIdList", questionsIdList);

		} catch (TechnicalException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;
	}

}
