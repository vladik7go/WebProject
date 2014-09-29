package by.epam.web.application.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import by.epam.web.application.resource.ConfigurationManager;

public class DBConnector {
	public static Logger log = Logger.getLogger(DBConnector.class);
	Properties properties = new Properties();

	// �����, ���������� ����������� ������ ��� �������� ����������. �����,
	// ����� ��� �������� ����, ���������� � ������� �������� ������ ���� ���.
	public String createConnection() {
		String url = ConfigurationManager.getProperty("db.url");// ����� ��
																// ��������
																// ����� url

		properties.setProperty("user", // ���������
										// ���������. Properties is
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
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());// ������������
																		// �������
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return url;

	}

	public Connection getConnection() {
		Connection connection = null;

		try {
			// ������� ���������� �� ��������� � ������� ��������, ���������
			// ������, ��������������� ������� "createConnection()"
			connection = DriverManager.getConnection(createConnection(),
					properties);

		} catch (SQLException e) {
			log.error("Technical Exception", e);
		}

		return connection;
	}
}