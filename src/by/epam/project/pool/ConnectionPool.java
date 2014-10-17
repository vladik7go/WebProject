package by.epam.project.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.epam.project.exception.TechnicalException;

public class ConnectionPool {
	public static Logger log = Logger.getLogger(ConnectionPool.class);
	private static Lock lock = new ReentrantLock();
	private final static int POOL_SIZE = 10;
	// static variable, in order to singleton fulfill
	private static ConnectionPool instance;
	private BlockingQueue<Connection> pool;

	private ConnectionPool() {// constructor
		createPool();
	}

	private void createPool() {// create pool and fill with connections
		pool = new ArrayBlockingQueue<Connection>(POOL_SIZE);

		DBConnector dbConnector = new DBConnector();
		for (int i = 0; i < POOL_SIZE; i++) {
			Connection connection = dbConnector.getConnection();
			try {
				pool.put(connection);
			} catch (InterruptedException e) {
				log.error("TechnicalException", e);
			}

		}
		log.debug("pool size = " + pool.size());
	}

	// This method realize pool. Pattern singleton used
	public static ConnectionPool getSinglePool() {
		if (instance == null) {

			try {
				lock.lock();// gain blocking of the instance
				if (instance == null) {
					instance = new ConnectionPool();
				}

			} finally {
				lock.unlock();// release blocking of the instance
			}
		}
		return instance;
	}

	// Gain a connection from the pool, queue is decreasing by one.
	public Connection getConnection() throws TechnicalException {
		Connection connection = null;
		try {
			connection = pool.take();
		} catch (InterruptedException e) {
			throw new TechnicalException("check connection for null ", e);
		}
		log.debug("pool size = " + pool.size());
		return connection;
	}

	// Return the connection to the pool
	public void returnConnection(Connection connection) {
		if (connection != null) {
			try {
				pool.put(connection);
			} catch (InterruptedException e) {
				log.error("TechnicalException", e);
			}
		}
		log.debug("pool size = " + pool.size());
	}

	public void cleanUpPool() {
		for (int i = 0; i < pool.size(); i++) {
			try {
				Connection connection = pool.take();
				connection.close();
			} catch (SQLException | InterruptedException e) {
				log.error("TechnicalException", e);
			}
		}
	}
}
