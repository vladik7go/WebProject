package by.epam.web.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.web.application.entity.Person;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.pool.ConnectionPool;

public class DaoPerson extends Dao {
	public static Logger log = Logger.getLogger(DaoPerson.class);

	private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "select login, password, role_type from person where login=? and password=?";
	private static final String SQL_ADD_PERSON = "INSERT INTO person (role_type, first_name, second_name, login, password) VALUES (?,?,?,?,?)";
	private static final String SQL_SHOW_PERSONS = "SELECT * FROM person";
	private static final String SQL_SHOW_PERSON_BY_ID = "SELECT * FROM person where id=? ";

	public DaoPerson() {

	}

	// метод, возвращает роль пользователя, если совпал логин и пароль. Иначе
	// возвращает ноль.
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

		} catch (SQLException e) {
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

		cn = ConnectionPool.getSinglePool().getConnection();
		try {
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
			throw new TechnicalException(e);
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return persons;

	}

	public Person showPerson(int id) {
		Connection cn = null;
		PreparedStatement st = null;
		Person person = new Person();

		cn = ConnectionPool.getSinglePool().getConnection();
		try {
			st = cn.prepareStatement(SQL_SHOW_PERSON_BY_ID);

			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			result.next();
			
			person.setRoleType(result.getInt("role_type"));
			person.setFirstName(result.getString("first_name"));
			person.setSecondName(result.getString("second_name"));
			person.setLogin(result.getString("login"));
			person.setPassword(result.getString("password"));
		} catch (SQLException e) {
			log.error("Technical Exception", e);
		}

		return person;
	}

	public void addPerson(String firstName, String SecondName, String login,
			String password) {

		Connection cn = null;
		PreparedStatement st = null;
		try {
			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_ADD_PERSON);
			st.setInt(1, 3);
			st.setString(2, firstName);
			st.setString(3, SecondName);
			st.setString(4, login);
			st.setString(5, password);
			st.executeUpdate();
		} catch (SQLException e) {
			log.error("Technical Exception", e);
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

	}

}
