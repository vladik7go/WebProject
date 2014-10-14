package by.epam.web.application.commands.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.person.EditPersonCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.entity.test.Question;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class ShowResultCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(ShowResultCommand.class);
	private static final String PARAM_NAME_ID = "personId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Map<String, Integer> resultMap = null;
		int personId = Integer.parseInt(((String) request.getSession().getAttribute(PARAM_NAME_ID)).trim());
		DaoTest dao = new DaoTest();
		try {
			resultMap = dao.showResults(personId);
		} catch (TechnicalException e) {
			log.error(e);
		}
		log.debug("debug" + resultMap.toString());
		page = ConfigurationManager.getProperty("path.page.show_result");
		request.setAttribute("resultMap", resultMap);
		return page;
	}

}
