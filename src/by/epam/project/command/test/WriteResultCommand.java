package by.epam.project.command.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoTest;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.TestLogic;
import by.epam.project.resource.ConfigurationManager;

public class WriteResultCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(WriteResultCommand.class);
	private static final String PARAM_NAME_ID = "personId";
	private static final String PARAM_TEST_ID = "testId";
	private static final String PARAM_TEST_MARK = "testMark";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int personId = Integer.parseInt(((String) request.getSession()
				.getAttribute(PARAM_NAME_ID)).trim());
		int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));
		int testMark = Integer.parseInt(request.getParameter(PARAM_TEST_MARK));
		DaoTest dao = new DaoTest();
		TestLogic logic = new TestLogic();
		boolean result = logic.writeResult(personId, testId, testMark);
		if (result) {
			page = ConfigurationManager.getProperty("path.page.show_result");
			request.setAttribute("successfullyPerformedAction", "1");
			request.setAttribute("testResultFinal", testMark);
			try {
				request.setAttribute("test", dao.showTest(testId));
			} catch (TechnicalException e) {
				log.error(e);
			}
		}

		return page;
	}

}
