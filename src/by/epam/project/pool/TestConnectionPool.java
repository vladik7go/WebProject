package by.epam.project.pool;


import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Test;

import by.epam.project.dao.DaoTest;
import by.epam.project.exception.TechnicalException;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.resource.ConfigurationManager;


public class TestConnectionPool {

	@Test
	public void getConnectionTest() throws TechnicalException {
		
		
		
		ConnectionPool conPoolInstance = ConnectionPool.getSinglePool();
		Connection connection = conPoolInstance.getConnection();
		Assert.assertNotNull(connection);
		
		ResourceBundle resourceBundle = ResourceBundle
				.getBundle("config");
		
		
		
	}

}
