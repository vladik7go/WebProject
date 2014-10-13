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
import by.epam.web.application.exceptions.TechnicalException;

public class TestLogic {
	public static Logger log = Logger.getLogger(TestLogic.class);
	int counter;

	public Map<Question, List<Answer>> enumerateQuestions(Test t) {
		List<Answer> answers = new ArrayList<>();
		Map<Question, List<Answer>> quiz = new HashMap<>();
		Set<Question> questions = null;
		Question question = null;
		Test test = t;
		questions = test.getQuestions();
		for (Question elem : questions) {
			answers = elem.getAnswers();

		}
		return quiz;
	}

	public Question testPerformer(Test t) {
		Question question = null;
		Set<Question> questions = null;
		Test test = t;
		counter = test.getQuestions().size();
		questions = test.getQuestions();
		for (Question elem : questions) {
			question = elem;
			System.out
					.println("number of answers= " + elem.getAnswers().size());

		}

		return question;
	}

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
			log.debug("answerId = " + elem.getId() + " ; answer value = "
					+ elem.getValue());
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
					log.debug("answers from logic= " + answer.getValue());
					counter += answer.getValue();

				}
			}
		} catch (TechnicalException e) {
			log.error(e);
		}

		System.out.println(testResult);
		System.out.println(counter);
		finalResult = (int) (((double) testResult / counter) * 5);

		return finalResult;

	}
}
