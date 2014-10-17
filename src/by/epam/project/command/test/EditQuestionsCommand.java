package by.epam.project.command.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoTest;
import by.epam.project.entity.test.Test;
import by.epam.project.exception.TechnicalException;
import by.epam.project.resource.ConfigurationManager;

public class EditQuestionsCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(EditQuestionsCommand.class);
	private static final String PARAM_NAME_ID = "testId";

	@Override
	public String execute(HttpServletRequest request) {
		Test test = null;
		String page = null;

		try {
			int testId = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
			DaoTest dao = new DaoTest();
			test = dao.showTest(testId);
			page = ConfigurationManager.getProperty("path.page.edit_questions");
			request.setAttribute("test", test);
		} catch (TechnicalException | NumberFormatException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;
	}

}
