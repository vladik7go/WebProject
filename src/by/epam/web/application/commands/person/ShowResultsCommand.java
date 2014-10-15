package by.epam.web.application.commands.person;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.test.ShowResultCommand;
import by.epam.web.application.dao.DaoPerson;
import by.epam.web.application.exceptions.LogicException;
import by.epam.web.application.resource.ConfigurationManager;

public class ShowResultsCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(ShowResultsCommand.class);
	private static final String PARAM_NAME_ID = "personId";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		List<List<String>> resultsList = null;
		int personId = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
		DaoPerson dao = new DaoPerson();
//		try {
			resultsList = dao.showResults(personId);
			if(resultsList.size()!=0){
			page = ConfigurationManager.getProperty("path.page.show_results");
			request.setAttribute("resultsList", resultsList);
			request.setAttribute("personId", personId);
			
			}else{
				request.setAttribute("errorEmptyResultTableMessage", "1");
				page = ConfigurationManager.getProperty("path.page.main_root");
			}

//		} catch (LogicException e) {
//			log.error(" Empty result table for this person", e);
//			request.setAttribute("errorEmptyResultTableMessage", "1");
//			page = ConfigurationManager.getProperty("path.page.main_root");
//
//		}

		return page;
	}

}
