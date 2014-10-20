package by.epam.project.command.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.command.person.ShowPersonsCommand;
import by.epam.project.dao.DaoPerson;
import by.epam.project.dao.DaoTest;
import by.epam.project.entity.test.Question;
import by.epam.project.entity.test.Test;
import by.epam.project.exception.TechnicalException;
import by.epam.project.resource.ConfigurationManager;

public class ShowTestsCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(ShowTestsCommand.class);

	@Override
	public String execute(HttpServletRequest request) {

		List<Test> tests = null;
		Map<Integer, Integer> resultMap = null;
		String page = null;
		DaoTest dao = new DaoTest();
		DaoPerson daoPerson = new DaoPerson();

		try {
			tests = dao.showTests();
			log.debug("list of tests = " + tests);
			request.setAttribute("testsList", tests);
			request.setAttribute("successfullyPerformedAction",
					request.getParameter("successfullyPerformedAction"));
			String role = (String) request.getSession().getAttribute("role");
			int personId = Integer.parseInt((String) request.getSession()
					.getAttribute("personId"));

			switch (role) {
			case "student":
				/*
				 * In order to place in the request - "result" and "person"
				 * attribute (HashMap object and bean object)
				 */
				resultMap = dao.showResult(personId);
				request.setAttribute("resultMap", resultMap);
				request.setAttribute("person", daoPerson.showPerson(personId));
				page = ConfigurationManager
						.getProperty("path.page.main_student");
				break;
			case "tutor":
				page = ConfigurationManager.getProperty("path.page.main_tutor");
				break;
			case "root":
				page = ConfigurationManager.getProperty("path.page.main_tutor");
				break;
			}
		} catch (TechnicalException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;
	}

}
