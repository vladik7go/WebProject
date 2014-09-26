package by.epam.web.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.epam.web.application.pool.ConnectionPool;

public class DaoPerson extends Dao {
	public static Logger log = Logger.getLogger(DaoPerson.class);

	private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "select login, password from person where login=? and password=?";
	private static final String SQL_ADD_PERSON = "INSERT INTO person (role_type, first_name, second_name, login, password) VALUES (?,?,?,?,?)";

	public DaoPerson() {

	}

	public boolean checkLogin(String name, String password) {

		Connection cn = null;
		PreparedStatement st = null;

		try {

			cn = ConnectionPool.getSinglePool().getConnection();
			st = cn.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
			st.setString(1, name);
			st.setString(2, password);
			ResultSet resultSet = st.executeQuery();
			resultSet.next();
			resultSet.getString("login");
			resultSet.getString("password");

		} catch (SQLException e) {
			log.error("TechnicalException", e);
			return false;
		} finally {
			Dao.closeStatement(st);
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return true;

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
