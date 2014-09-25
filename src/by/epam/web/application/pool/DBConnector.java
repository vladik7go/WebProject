package by.epam.web.application.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.sun.xml.internal.bind.v2.util.FatalAdapter;

import by.epam.web.application.resource.ConfigurationManager;
import static by.epam.web.application.controller.Controller.LOG;

public class DBConnector {
	Properties properties = new Properties();

	public String createConnection() {
		String url = ConfigurationManager.getProperty("db.url");// берем из
																// проперти
																// файла url

		properties.setProperty("user", // заполняем
										// пропертис. Properties is
										// a
										// subclass of Hashtable. It
				// is used to maintain lists
				// of values in which the
				// key is a String and the
				// value is also a String.
				ConfigurationManager.getProperty("db.user"));
		properties.setProperty("password",
				ConfigurationManager.getProperty("db.password"));
		properties.setProperty("useUnicode",
				ConfigurationManager.getProperty("db.useUnicode"));
		properties.setProperty("characterEncoding",
				ConfigurationManager.getProperty("db.encoding"));

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());// регистрируем драйвер
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return url;

	}

	public Connection getConnection() {
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(createConnection(),
					properties);// создаем
			// соединение
		} catch (SQLException e) {
			LOG.error("Technical Exception", e);
		}

		return connection;
	}
}