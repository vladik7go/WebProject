package by.epam.web.application.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public abstract class Dao {
	public static  Logger log = Logger.getLogger(Dao.class);
	
	public static void closeStatement(Statement st){
		if (st != null){
			try {
				st.close();
			} catch (SQLException e) {
				log.error("Technical Exception", e);
			}
		}
	}

}
