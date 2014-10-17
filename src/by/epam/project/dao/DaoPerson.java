package by.epam.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.project.entity.Person;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.pool.ConnectionPool;

public class DaoPerson extends Dao {
	public static Logger log = Logger.getLogger(DaoPerson.class);

	private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "select * from person where login=? and password=?";
	private static final String SQL_ADD_PERSON = "INSERT INTO person (role_type, first_name, second_name, login, password) VALUES (?,?,?,?,?)";
	private static final String SQL_EDIT_PERSON = "update person SET role_type=?, first_name=?, second_name=?, login=?, password=? where id=?";
	private static final String SQL_SHOW_PERSONS = "SELECT * FROM person";
	private static final String SQL_SHOW_PERSON_BY_ID = "SELECT * FROM person where id=? ";
	private static final String SQL_DELETE_PERSON_BY_ID = "DELETE from person where id= ?";
	private static final String SQL_SHOW_RESULTS_BY_PERSON_ID = "SELECT person.first_name, person.second_name, test.title, test.description, result.mark "
			+ "FROM result "
			+ "JOIN person ON result.person_type=person.id "
			+ "JOIN test ON result.test_type=test.id "
			+ "WHERE result.person_type=?";

	/*
	 * This method return the role of the user in case, if proper login and
	 * password was entered. Otherwise - return zero.
	 */
	public int checkLogin(String name, String password) {

		Connection cn = null;
		PreparedStatement st = null;
		int roleType = 0;
		try {

			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
			st.setString(1, name);
			st.setString(2, password);
			ResultSet resultSet = st.executeQuery();
			resultSet.next();
			resultSet.getString("login");
			resultSet.getString("password");
			roleType = resultSet.getInt("role_type");

		} catch (SQLException | TechnicalException e) {
			log.error("Technical Exception", e);
			return 0;
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return roleType;

	}

	public List<Person> showPersons() throws TechnicalException {
		List<Person> persons = new ArrayList<Person>();
		Person person = null;
		Connection cn = null;
		Statement st = null;

		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.createStatement();
			ResultSet result = st.executeQuery(SQL_SHOW_PERSONS);
			while (result.next()) {
				person = new Person();

				person.setId(result.getInt(1));
				person.setRoleType(result.getInt(2));
				person.setFirstName(result.getString(3));
				person.setSecondName(result.getString(4));
				person.setLogin(result.getString(5));
				person.setPassword(result.getString(6));
				persons.add(person);
			}

		} catch (SQLException e) {
			throw new TechnicalException("DaoPerson.showPersons", e);
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return persons;

	}

	public List<List<String>> showResults(int personId) {
		List<List<String>> resultsList = new ArrayList<>();
		Connection cn = null;
		PreparedStatement st = null;

		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_SHOW_RESULTS_BY_PERSON_ID);
			st.setInt(1, personId);
			ResultSet result = st.executeQuery();
			// result.next();
			// resultsList.add(result.getString(1));
			// resultsList.add(result.getString(2));

			while (result.next()) {
				List<String> list = new ArrayList<>();
				list.add(result.getString(1));
				list.add(result.getString(2));
				list.add(result.getString(3));
				list.add(result.getString(4));
				list.add(String.valueOf(result.getInt(5)));

				resultsList.add(list);
			}

		} catch (SQLException | TechnicalException e) {
			log.error(e);
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return resultsList;
	}

	public Person showPerson(int id) throws TechnicalException {
		Connection cn = null;
		PreparedStatement st = null;
		Person person = new Person();

		cn = ConnectionPool.getSinglePool().getConnection();
		try {
			st = cn.prepareStatement(SQL_SHOW_PERSON_BY_ID);

			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			result.next();
			person.setId(result.getInt("id"));
			person.setRoleType(result.getInt("role_type"));
			person.setFirstName(result.getString("first_name"));
			person.setSecondName(result.getString("second_name"));
			person.setLogin(result.getString("login"));
			person.setPassword(result.getString("password"));
		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return person;
	}

	public Person showPerson(String name, String password)
			throws TechnicalException {
		Connection cn = null;
		PreparedStatement st = null;
		Person person = new Person();
		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
			st.setString(1, name);
			st.setString(2, password);
			ResultSet result = st.executeQuery();
			result.next();
			person.setId(result.getInt("id"));
			person.setRoleType(result.getInt("role_type"));
			person.setFirstName(result.getString("first_name"));
			person.setSecondName(result.getString("second_name"));
			person.setLogin(result.getString("login"));
			person.setPassword(result.getString("password"));
		} catch (SQLException e) {
			throw new TechnicalException(e);
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return person;
	}

	public boolean addPerson(String firstName, String SecondName, String login,
			String password, int role) {

		Connection cn = null;
		PreparedStatement st = null;
		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_ADD_PERSON);
			st.setInt(1, role);
			st.setString(2, firstName);
			st.setString(3, SecondName);
			st.setString(4, login);
			st.setString(5, password);

			st.executeUpdate();
		} catch (SQLException | TechnicalException e) {
			log.error("Technical Exception", e);
			return false;
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}
		return true;
	}

	public boolean editPerson(int id, int role, String firstName,
			String SecondName, String login, String password) {
		Connection cn = null;
		PreparedStatement st = null;

		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_EDIT_PERSON);
			st.setInt(1, role);
			st.setString(2, firstName);
			st.setString(3, SecondName);
			st.setString(4, login);
			st.setString(5, password);
			st.setInt(6, id);
			log.debug("edit user, id = " + id);
			st.executeUpdate();
		} catch (SQLException | TechnicalException e) {
			log.error("Technical Exception", e);
			return false;
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return true;
	}

	public boolean deletePerson(int id) {
		Connection cn = null;
		PreparedStatement st = null;
		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_DELETE_PERSON_BY_ID);
			st.setInt(1, id);
			st.executeUpdate();
			return true;
		} catch (SQLException | TechnicalException e) {
			log.error(e);
			return false;
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

	}

}
