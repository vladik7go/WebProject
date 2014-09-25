package by.epam.web.application.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.web.application.controller.Controller.LOG;

public class ConnectionPool {
	private static Lock lock = new ReentrantLock();
	private final static int POOL_SIZE = 10;
	private static ConnectionPool instance;// ����������� ���������� ���
											// ���������� ���������
	private BlockingQueue<Connection> pool;

	private ConnectionPool() {// constructor
		createPool();
	}

	private void createPool() {// ������� ��� � ��������� ������������
		pool = new ArrayBlockingQueue<Connection>(POOL_SIZE);

		for (int i = 0; i < POOL_SIZE; i++) {
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			try {
				pool.put(connection);
			} catch (InterruptedException e) {
				LOG.error("TechnicalException", e);
			}

		}
		System.out.println("pool size = " + pool.size());
	}

	public static ConnectionPool getSinglePool() {// ����� ��� ��������� ����,
													// ������� ��������.
		if (instance == null) {

			try {
				lock.lock();// �������� ���������� ����������
				if (instance == null) {
					instance = new ConnectionPool();
				}

			} finally {
				lock.unlock();// ����������� ���������� ����������
			}
		}
		return instance;
	}

	public Connection getConnection() {// �������� ���� ���������� �� ����,
										// �������
										// �����������
		Connection connection = null;
		try {
			connection = pool.take();
		} catch (InterruptedException e) {
			LOG.error("Technical Exception", e);
		}
		System.out.println("pool size = " + pool.size());
		return connection;
	}

	public void returnConnection(Connection connection) {// ����������
															// ���������� � ���
		if (connection != null) {
			try {
				pool.put(connection);
			} catch (InterruptedException e) {
				LOG.error("TechnicalException", e);
			}
		}
		System.out.println("pool size = " + pool.size());
	}

	public void cleanUpPool() {
		for (int i = 0; i < pool.size(); i++) {
			try {
				Connection connection = pool.take();
				connection.close();
			} catch (SQLException | InterruptedException e) {
				LOG.error("TechnicalException", e);
			}
		}
	}
}