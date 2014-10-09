package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class AddTestCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(AddTestCommand.class);
	private static final String PARAM_NAME_TEST_TITLE = "testTitle";
	private static final String PARAM_NAME_TEST_DESCRIPTION = "testDescription";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String testTitle = request.getParameter(PARAM_NAME_TEST_TITLE).trim();
		String testDescription = request.getParameter(
				PARAM_NAME_TEST_DESCRIPTION).trim();
		// Checking for empty fields
		DaoTest dao = new DaoTest();
		if (testTitle.length() * testDescription.length() != 0) {
			boolean result = dao.addTest(testTitle, testDescription);
			if (result) {
				page = ConfigurationManager.getProperty("path.page.main_tutor");
				request.setAttribute("successfullyPerformedAction", "1");
				try {
					request.setAttribute("testsList", dao.showTests());
				} catch (TechnicalException e) {
					log.error(e);
				}
			} else {
				page = ConfigurationManager.getProperty("path.page.main_tutor");
				request.setAttribute("successfullyPerformedAction", "0");
			}
		} else {
			request.setAttribute("errorEmptyFieldMessage", "true");
			try {
				request.setAttribute("testsList", dao.showTests());

			} catch (TechnicalException e) {
				log.error(e);
			}
			page = ConfigurationManager.getProperty("path.page.main_tutor");
		}

		return page;
	}

}
