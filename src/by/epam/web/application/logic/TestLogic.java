package by.epam.web.application.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.test.EditWriteQuestionCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.entity.test.Answer;
import by.epam.web.application.entity.test.Question;
import by.epam.web.application.entity.test.Test;
import by.epam.web.application.exceptions.LogicException;
import by.epam.web.application.exceptions.TechnicalException;

public class TestLogic {
	public static Logger log = Logger.getLogger(TestLogic.class);

	public int checkQuestion(int questionId, String[] answerValues) {
		int tmpResult = 0;
		int counterForTrue = 0;
		List<Answer> answersList = new ArrayList<>();

		DaoTest dao = new DaoTest();
		try {
			answersList = dao.showQuestion(questionId).getAnswers();
		} catch (TechnicalException e) {
			log.error(e);
		}

		for (Answer elem : answersList) {
			log.debug("From TestLogic.checkQuestion: answerId = "
					+ elem.getId() + " ; answer value = " + elem.getValue());
			counterForTrue += elem.getValue();

			for (String s : answerValues) {
				int valueFromJSP = Integer.parseInt(s);
				if (valueFromJSP == elem.getId() && elem.getValue() == 1) {
					tmpResult += 1;
				}
				if (valueFromJSP == elem.getId() && elem.getValue() == 0) {
					tmpResult -= 1;
				}

			}
		}
		if (counterForTrue == tmpResult) {
			return tmpResult;

		} else {
			return tmpResult;
		}
	}

	public int calculateResult(int testResult, int testId) {

		int counter = 0;

		int finalResult = 0;
		DaoTest dao = new DaoTest();
		try {
			Test test = dao.showTest(testId);

			Set<Question> questions = test.getQuestions();

			for (Question question : questions) {

				List<Answer> answers = question.getAnswers();

				for (Answer answer : answers) {
					log.debug("From TestLogic.calculateResult: answers(real) from logic= "
							+ answer.getValue());
					counter += answer.getValue();

				}
			}
		} catch (TechnicalException e) {
			log.error(e);
		}

		finalResult = (int) (((double) testResult / counter) * 10);
		return finalResult;

	}

	public boolean writeResult(int personId, int testId, int testMark) {

		DaoTest dao = new DaoTest();
		try {
			dao.addResult(personId, testId, testMark);
		} catch (LogicException e) {
			log.error(
					"From TestLogic.writeResult: pair 'personId===testType' already exist. Should be unique",
					e);
			dao.deleteResult(personId, testId);
			log.debug("From TestLogic.writeResult: existed pair 'personId===testType' successfully deleted");
			try {
				dao.addResult(personId, testId, testMark);
			} catch (LogicException e1) {
				log.error(e1);
			}
			log.debug("From TestLogic.writeResult: NEW pair 'personId===testType' successfully added");
		}

		return true;
	}
}
