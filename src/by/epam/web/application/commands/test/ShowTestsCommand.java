package by.epam.web.application.commands.test;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.person.ShowPersonsCommand;
import by.epam.web.application.dao.DaoTest;
import by.epam.web.application.entity.test.Question;
import by.epam.web.application.entity.test.Test;
import by.epam.web.application.exceptions.TechnicalException;
import by.epam.web.application.resource.ConfigurationManager;

public class ShowTestsCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(ShowTestsCommand.class);
	@Override
	
	
	
	public String execute(HttpServletRequest request) {

		List<Test> tests = null;
		String page = null;
		DaoTest dao = new DaoTest();
		
		try {
			tests = dao.showTests();
			log.debug("list of tests = " + tests);
			request.setAttribute("testsList", tests);
			request.setAttribute("successfullyPerformedAction",
					request.getParameter("successfullyPerformedAction"));
			page = ConfigurationManager.getProperty("path.page.main_tutor");
		} catch (TechnicalException e) {
			log.error(e);
			page = ConfigurationManager.getProperty("path.page.login");
		}
		
		return page;
	}

}
