package by.epam.project.command.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.command.person.EditPersonCommand;
import by.epam.project.dao.DaoTest;
import by.epam.project.entity.test.Question;
import by.epam.project.exception.TechnicalException;
import by.epam.project.resource.ConfigurationManager;

public class ShowResultCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(ShowResultCommand.class);
	private static final String PARAM_NAME_ID = "personId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Map<Integer, Integer> resultMap = null;
		int personId = Integer.parseInt(((String) request.getSession().getAttribute(PARAM_NAME_ID)).trim());
		DaoTest dao = new DaoTest();
		try {
			resultMap = dao.showResult(personId);
		} catch (TechnicalException e) {
			log.error(e);
		}
		log.debug("debug" + resultMap.toString());
		page = ConfigurationManager.getProperty("path.page.show_result");
		request.setAttribute("resultMap", resultMap);
		return page;
	}

}
