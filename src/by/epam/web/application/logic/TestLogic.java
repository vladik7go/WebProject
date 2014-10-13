package by.epam.web.application.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.epam.web.application.entity.test.Answer;
import by.epam.web.application.entity.test.Question;
import by.epam.web.application.entity.test.Test;

public class TestLogic {
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
			System.out.println("number of answers= "+elem.getAnswers().size());

		}

		return question;
	}
}
