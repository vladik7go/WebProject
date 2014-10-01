package by.epam.web.application.commands.person;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.dao.DaoPerson;
import by.epam.web.application.entity.Person;
import by.epam.web.application.resource.ConfigurationManager;

public class DeletePerson implements ActionCommand {
	public static Logger log = Logger.getLogger(DeletePerson.class);
	private static final String PARAM_NAME_ID = "personId";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		// ���������� �� ������� ����������
		int personId = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
		DaoPerson dao = new DaoPerson();
		boolean result = dao.deletePerson(personId);
		if (result) {
			page = ConfigurationManager.getProperty("path.page.main_root");
			request.setAttribute("successfullyPerformedAction", "1");
		} else {
			page = ConfigurationManager.getProperty("path.page.login");
			request.setAttribute("successfullyPerformedAction", "0");
		}

		return page;
	}

}
