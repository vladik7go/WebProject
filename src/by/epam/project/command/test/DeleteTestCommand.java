package by.epam.project.command.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoTest;
import by.epam.project.exception.TechnicalException;
import by.epam.project.resource.ConfigurationManager;

public class DeleteTestCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(DeleteTestCommand.class);
	private static final String PARAM_TEST_ID = "testId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));

		DaoTest dao = new DaoTest();
		boolean result = dao.deleteTest(testId);
		if (result) {
			page = ConfigurationManager.getProperty("path.page.main_tutor");
			request.setAttribute("successfullyPerformedAction", "1");
			try {
				request.setAttribute("testsList", dao.showTests());
			} catch (TechnicalException e) {
				log.error(e);
			}
		} else {
			page = ConfigurationManager.getProperty("path.page.login");
			request.setAttribute("successfullyPerformedAction", "0");
		}

		return page;
	}

}
