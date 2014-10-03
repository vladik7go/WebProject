package by.epam.web.application.commands.test;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.person.ShowPersonsCommand;
import by.epam.web.application.entity.test.Question;

public class ShowTests implements ActionCommand {
	public static Logger log = Logger.getLogger(ShowTests.class);
	@Override
	
	
	
	public String execute(HttpServletRequest request) {

		Set<Question> questions = null;
		String page = null;
		
		
		
		return page;
	}

}
