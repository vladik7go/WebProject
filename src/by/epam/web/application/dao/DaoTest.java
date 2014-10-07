package by.epam.web.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import by.epam.web.application.entity.Person;
import by.epam.web.application.entity.test.Answer;
import by.epam.web.application.entity.test.Question;
import by.epam.web.application.entity.test.Test;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.pool.ConnectionPool;

public class DaoTest extends Dao {
	public static Logger log = Logger.getLogger(DaoTest.class);

	private static final String SQL_SHOW_TEST_BY_ID = "SELECT * FROM test where id=? ";
	private static final String SQL_SHOW_QUESTION_BY_ID = "SELECT * FROM question where id=? ";
	private static final String SQL_SHOW_QUESTIONS_BY_TEST_TYPE = "SELECT * FROM question where test_type=? ";
	private static final String SQL_SHOW_ANSWERS_BY_QUESTION_TYPE = "SELECT * FROM answer where question_type=? ";
	private static final String SQL_SHOW_TESTS_ID = "SELECT id FROM test";
	private static final String SQL_EDIT_TEST = "update test SET title=?, description=? where id=?";
	private static final String SQL_EDIT_QUESTION = "update question SET test_type=?, content=? where id=?";
	private static final String SQL_DELETE_ANSWER_BY_ID = "DELETE from answer where id= ?";

	/*
	 * This method return an object Test, which aggregate Set of objects
	 * Question, which in it turn contains List of objects Answers.
	 */
	public Test showTest(int id) throws TechnicalException {

		Connection cn = null;
		PreparedStatement st = null;
		PreparedStatement statementForQuestions = null;
		PreparedStatement statementForAnswers = null;
		Test test = null;

		cn = ConnectionPool.getSinglePool().getConnection();

		try {
			st = cn.prepareStatement(SQL_SHOW_TEST_BY_ID);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			result.next();

			// ---Extracting from table "question" all questions according to
			// type of the test.
			Set<Question> questionsSet = new HashSet<>();
			statementForQuestions = cn
					.prepareStatement(SQL_SHOW_QUESTIONS_BY_TEST_TYPE);
			statementForQuestions.setInt(1, id);
			ResultSet resultQuestions = statementForQuestions.executeQuery();
			while (resultQuestions.next()) {

				int typeOfQuestion = resultQuestions.getInt("test_type");

				// ---Extracting from table "answer" all answers according to
				// type of the question, and save them in object "answer".
				List<Answer> answersList = new ArrayList<>();
				statementForAnswers = cn
						.prepareStatement(SQL_SHOW_ANSWERS_BY_QUESTION_TYPE);
				statementForAnswers.setInt(1, typeOfQuestion);
				ResultSet resultAnswers = statementForAnswers.executeQuery();
				while (resultAnswers.next()) {
					Answer answer = new Answer();
					answer.setId(resultAnswers.getInt("id"));
					answer.setQuestionType(resultAnswers
							.getInt("question_type"));
					answer.setAnswer(resultAnswers.getString("answer"));
					answer.setValue(resultAnswers.getInt("value"));
					// Save already filled object "answer" in the List.
					answersList.add(answer);
				}
				// ---Extracting from table "question" all questions according
				// to type of the test, and save them in object "question"

				Question question = new Question();
				question.setId(resultQuestions.getInt("id"));
				question.setTestType(typeOfQuestion);
				question.setContent(resultQuestions.getString("content"));
				question.setAnswers(answersList);
				// Save already filled object "question" in the Set.
				questionsSet.add(question);
			}

			// --------------
			test = new Test();
			test.setId(result.getInt("id"));
			test.setTitle(result.getString("title"));
			test.setDescription(result.getString("description"));
			test.setQuestions(questionsSet);

		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			Dao.closeStatement(st);
			Dao.closeStatement(statementForQuestions);
			Dao.closeStatement(statementForAnswers);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}
		log.debug("DAO method 'showTest' performed and return = " + test);
		return test;
	}

	/*
	 * This method return List filled with objects "Test"
	 */
	public List<Test> showTests() throws TechnicalException {
		List<Test> tests = new ArrayList<>();
		Connection cn = null;
		Statement st = null;

		cn = ConnectionPool.getSinglePool().getConnection();
		try {
			st = cn.createStatement();
			ResultSet result = st.executeQuery(SQL_SHOW_TESTS_ID);
			while (result.next()) {
				Test test = showTest(result.getInt("id"));
				tests.add(test);

			}
		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return tests;
	}

	public boolean editTest(int id, String title, String description) {
		Connection cn = null;
		PreparedStatement st = null;

		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_EDIT_TEST);
			st.setString(1, title);
			st.setString(2, description);
			st.setInt(3, id);
			st.executeUpdate();
		} catch (SQLException e) {
			log.error("Technical Exception", e);
			return false;
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return true;
	}

	public Question showQuestion(int id) throws TechnicalException {
		Connection cn = null;
		PreparedStatement st = null;
		PreparedStatement statementForAnswers = null;
		Question question = null;

		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_SHOW_QUESTION_BY_ID);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			result.next();
			int typeOfQuestion = result.getInt("id");
			List<Answer> answersList = new ArrayList<>();
			statementForAnswers = cn
					.prepareStatement(SQL_SHOW_ANSWERS_BY_QUESTION_TYPE);
			statementForAnswers.setInt(1, typeOfQuestion);
			ResultSet resultAnswers = statementForAnswers.executeQuery();
			while (resultAnswers.next()) {
				Answer answer = new Answer();
				answer.setId(resultAnswers.getInt("id"));
				answer.setQuestionType(resultAnswers.getInt("question_type"));
				answer.setAnswer(resultAnswers.getString("answer"));
				answer.setValue(resultAnswers.getInt("value"));
				// Save already filled object "answer" in the List.
				answersList.add(answer);
			}
			question = new Question();
			question.setId(result.getInt("id"));
			question.setTestType(typeOfQuestion);
			question.setContent(result.getString("content"));
			question.setAnswers(answersList);

		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			Dao.closeStatement(st);
			Dao.closeStatement(statementForAnswers);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return question;
	}

	public boolean editQuestion(int id, int testType, String content) {
		Connection cn = null;
		PreparedStatement st = null;

		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_EDIT_QUESTION);
			st.setInt(1, testType);
			st.setString(2, content);
			st.setInt(3, id);
			st.executeUpdate();
		} catch (SQLException e) {
			log.error("Technical Exception", e);
			return false;
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return true;

	}

	public boolean deleteAnswer(int id) {
		Connection cn = null;
		PreparedStatement st = null;
		cn = ConnectionPool.getSinglePool().getConnection();

		try {
			st = cn.prepareStatement(SQL_DELETE_ANSWER_BY_ID);
			st.setInt(1, id);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			log.error(e);
			return false;
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}
	}
}
