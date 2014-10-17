package by.epam.project.command.person;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.project.command.ActionCommand;
import by.epam.project.dao.DaoPerson;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.LoginLogic;
import by.epam.project.resource.ConfigurationManager;

public class EditWritePersonCommand implements ActionCommand {
	public static Logger log = Logger.getLogger(EditWritePersonCommand.class);

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_FIRST_NAME = "firstName";
	private static final String PARAM_NAME_SECOND_NAME = "secondName";
	private static final String PARAM_NAME_ROLE = "role";
	private static final String PARAM_NAME_ID = "id";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		// Extracting parameters from the request
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		String firstName = request.getParameter(PARAM_NAME_FIRST_NAME);
		String secondName = request.getParameter(PARAM_NAME_SECOND_NAME);
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_ID).trim());
		log.debug("String role = " + request.getParameter(PARAM_NAME_ROLE));
		try {
			int role = Integer.parseInt(request.getParameter(PARAM_NAME_ROLE)
					.trim());
			log.debug("int role = " + role);
			if (role < 1 && role > 3) {
				log.debug("Role does not equal 1,2 or 3");
				throw new LogicException("Role does not equal 1,2 or 3");
			}
			if (firstName.length() * secondName.length() * login.length()
					* pass.length() == 0) {

				request.setAttribute("errorEmptyFieldMessage", "true");
				page = ConfigurationManager.getProperty("path.page.edit_user");
			} else {
				DaoPerson dao = new DaoPerson();
				LoginLogic logic = new LoginLogic();
				String passMd5 = logic.getHash(pass);
				boolean result = dao.editPerson(id, role, firstName,
						secondName, login, passMd5);

				// Return string with the address of the login page 
				if (result) {
					page = ConfigurationManager
							.getProperty("path.page.main_root");
					request.setAttribute("successfullyPerformedAction", "1");
				}else{
					page = ConfigurationManager
							.getProperty("path.page.main_root");
					request.setAttribute("successfullyPerformedAction", "0");
				}
				log.debug("command editWrite performed. Result = " + result);
				log.debug(request.getAttribute("successfullyPerformedAction"));
			}

		} catch (NumberFormatException | LogicException e) {
			log.error(e);
			request.setAttribute("errorNonNumberMessage", "true");
			page = ConfigurationManager.getProperty("path.page.edit_user");
		}
		
		return page;
	}

}
