package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class EditWriteTestCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(EditWriteTestCommand.class);
	private static final String PARAM_NAME_ID = "id";
	private static final String PARAM_NAME_TITLE = "title";
	private static final String PARAM_NAME_DESCRIPTION = "description";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_ID).trim());
		String title = request.getParameter(PARAM_NAME_TITLE).trim();
		String description = request.getParameter(PARAM_NAME_DESCRIPTION).trim();

		// Checking for empty fields
		DaoTest dao = new DaoTest();
		if (title.length() * description.length() * id != 0) {

			boolean result = dao.editTest(id, title, description);
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
			page = ConfigurationManager.getProperty("path.page.edit_test");
			request.setAttribute("errorEmptyFieldMessage", "true");
			try {
				request.setAttribute("test", dao.showTest(id));
			} catch (TechnicalException e) {
				log.error(e);
			}
		}

		return page;
	}

}
