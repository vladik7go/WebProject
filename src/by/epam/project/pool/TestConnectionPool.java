package by.epam.project.pool;


import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import by.epam.project.dao.DaoTest;
import by.epam.project.exception.TechnicalException;
import by.epam.project.pool.ConnectionPool;


public class TestConnectionPool {

	@Test
	public void getConnectionTest() throws TechnicalException {
		
		
		DaoTest dao = new DaoTest();
		List<by.epam.project.entity.test.Test> tests = dao.showTests();
		Assert.assertNotNull(tests);
		
		
	}

}
