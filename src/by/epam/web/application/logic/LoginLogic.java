package by.epam.web.application.logic;

import by.epam.web.application.dao.Dao;

public class LoginLogic {

	

	Dao dao = Dao.getDao();
	
	public  boolean checkLogin(String enterLogin, String enterPass) {
		
		return dao.checkLogin(enterLogin, enterPass);
		
		
		
		
	}

}
