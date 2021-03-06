package by.epam.project.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import by.epam.project.command.test.EditWriteQuestionCommand;
import by.epam.project.dao.DaoTest;
import by.epam.project.entity.test.Answer;
import by.epam.project.entity.test.Question;
import by.epam.project.entity.test.Test;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.TechnicalException;

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

		return tmpResult;

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
			log.info(
					"From TestLogic.writeResult: pair 'personId===testType' already exist. Should be unique",
					e);
			dao.deleteResult(personId, testId);
			log.info("From TestLogic.writeResult: existed pair 'personId===testType' successfully deleted");
			try {
				dao.addResult(personId, testId, testMark);
			} catch (LogicException e1) {
				log.error("Logic exception", e1);
			}
			log.info("From TestLogic.writeResult: NEW pair 'personId===testType' successfully added");

		}
		return true;
	}

	public boolean checkTestForPerformed(int testId) {
		DaoTest dao = new DaoTest();
		boolean result = dao.checkTest(testId);
		if (result) {

			return true;
		} else {
			return false;
		}

	}
}
