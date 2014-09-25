package by.epam.web.application.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;


import by.epam.web.application.pool.ConnectionPool;



public class Dao {
	public static final Logger LOG = Logger.getLogger(Dao.class);

	public static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "select login, password from users where login=? and password=?";

	private static Dao thisDao;

	private Dao() {

	}

	public static synchronized Dao getDao() {
		if (thisDao == null)
			thisDao = new Dao();
		return thisDao;
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
			LOG.error(e);
			return false;
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
			ConnectionPool.getSinglePool().returnConnection(cn);
		}

		return true;

	}

}
