package by.epam.project.command.test;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoPerson;
import by.epam.project.dao.DaoTest;
import by.epam.project.entity.test.Test;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.TestLogic;
import by.epam.project.resource.ConfigurationManager;

public class PerformWriteTestCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(PerformWriteTestCommand.class);
	private static final String PARAM_NAME_ANSWER_VARIANT = "answerVariant";
	private static final String PARAM_TEST_ID = "testId";
	private static final String PARAM_NAME_QUESTION_ID = "questionId";
	private static final String PARAM_NAME_QUESTIONS_ID_LIST = "questionsIdList";
	private static final String PARAM_NAME_TEST_RESULT = "testResult";
	private static final String PARAM_NAME_ID = "personId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int testResultInt = 0;
		int personId = Integer.parseInt(((String) request.getSession()
				.getAttribute(PARAM_NAME_ID)).trim());
		ArrayList<Integer> questionsIdList = (ArrayList<Integer>) request
				.getSession().getAttribute(PARAM_NAME_QUESTIONS_ID_LIST);

		String[] answerValues = request
				.getParameterValues(PARAM_NAME_ANSWER_VARIANT);
		DaoTest dao = new DaoTest();
		DaoPerson daoPerson = new DaoPerson();
		TestLogic logic = new TestLogic();
		try {
			testResultInt = Integer.parseInt(request
					.getParameter(PARAM_NAME_TEST_RESULT));
		} catch (NumberFormatException e2) {
			log.error("Technical exception in PerformWriteTestCommand", e2);
			testResultInt = 0;
			
		}
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));
		int questionId = Integer.parseInt(request
				.getParameter(PARAM_NAME_QUESTION_ID));
		
		try {
			// for (int i = 0; answerValues.length > i; i++) {
			// log.debug("value " + i + " = " + answerValues[i]);
			int result = logic.checkQuestion(questionId, answerValues);
			log.debug("From PerformWriteTest: result(within this question) = " + result);
			if (result > 0) {
				testResultInt += result;
			}
			log.debug("From PerformWriteTest: testResult(summary) =  " + testResultInt);
			request.setAttribute("testResult", testResultInt);

			// }
		} catch (NullPointerException e) {
			log.error("the checkbox is empty", e);
			request.setAttribute("errorEmptyFieldMessage", "true");

		}
		try {
			questionsIdList.remove(0);
			request.setAttribute("question",
					dao.showQuestion(questionsIdList.get(0)));
			request.setAttribute("test", dao.showTest(testId));
			request.setAttribute(PARAM_NAME_QUESTIONS_ID_LIST, questionsIdList);
			request.setAttribute("person", daoPerson.showPerson(personId));
			page = ConfigurationManager.getProperty("path.page.perform_test");
		} catch (TechnicalException | IndexOutOfBoundsException e1) {
			log.error("Technical exception in PerformWriteTestCommand. ", e1);
			int testResultFinal = logic.calculateResult(testResultInt, testId);
			page = ConfigurationManager.getProperty("path.page.show_result");
			request.setAttribute("testResultFinal", testResultFinal);
			try {
				request.setAttribute("test", dao.showTest(testId));
			} catch (TechnicalException e) {
				log.error(e);
			}

		}
		return page;
	}

}
