package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.entity.test.Test;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class PerformTestCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(PerformTestCommand.class);
	private static final String PARAM_TEST_ID = "testId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Test test = null;
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));

		DaoTest dao = new DaoTest();
		try {
			test = dao.showTest(testId);
			page = ConfigurationManager.getProperty("path.page.perform_test");
			request.setAttribute("test", test);

		} catch (TechnicalException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;
	}

}
