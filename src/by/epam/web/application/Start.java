package by.epam.web.application;

import java.sql.SQLException;

import by.epam.web.application.pool.ConnectionPool;
import by.epam.web.application.pool.DBConnector;



public class Start {

	public static void main(String[] args) throws SQLException {


		
		
		ConnectionPool pool = ConnectionPool.getSinglePool();
		
	}

}
