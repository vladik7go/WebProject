package by.epam.web.application.logic;

import by.epam.web.application.dao.DaoPerson;

public class LoginLogic {

	DaoPerson dao = new DaoPerson();

	public int checkLogin(String enterLogin, String enterPass) {

		return dao.checkLogin(enterLogin, enterPass);

	}

}
