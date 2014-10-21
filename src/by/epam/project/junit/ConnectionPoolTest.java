package by.epam.project.junit;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import by.epam.project.exception.TechnicalException;
import by.epam.project.pool.ConnectionPool;

public class ConnectionPoolTest {
	@Test
	public void GetConnectionTest() throws TechnicalException {
		ConnectionPool pool = ConnectionPool.getSinglePool();
		Connection connection = pool.getConnection();
		Assert.assertNull(connection);
		
	}

}
