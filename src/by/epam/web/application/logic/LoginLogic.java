package by.epam.web.application.logic;

import by.epam.web.application.dao.DaoPerson;
import by.epam.web.application.exceptions.TechnicalException;

public class LoginLogic {

	DaoPerson dao = new DaoPerson();

	public int checkLogin(String enterLogin, String enterPass) throws TechnicalException {

		return dao.checkLogin(enterLogin, enterPass);

	}

}
