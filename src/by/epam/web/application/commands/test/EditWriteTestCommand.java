package by.epam.web.application.commands.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.person.EditWritePersonCommand;
import by.epam.web.application.dao.DaoTest;
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
		String title = request.getParameter(PARAM_NAME_TITLE);
		String description = request.getParameter(PARAM_NAME_DESCRIPTION);
		
		
		//Checking for empty fields
		if (title.length() * description.length() * id != 0) {
			
		
		
		DaoTest dao = new DaoTest();
		boolean result = dao.editTest(id, title, description);
		if (result){
			page = ConfigurationManager
					.getProperty("path.page.main_tutor");
			request.setAttribute("successfullyPerformedAction", "1");
		}else{
			page = ConfigurationManager
					.getProperty("path.page.main_tutor");
			request.setAttribute("successfullyPerformedAction", "0");
		}
	
	}else{
		request.setAttribute("errorEmptyFieldMessage", "true");
		page = ConfigurationManager.getProperty("path.page.edit_test");
	}

		
		
		
		return page;
	}

}
