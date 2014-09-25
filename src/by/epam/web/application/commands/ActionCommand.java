package by.epam.web.application.commands;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

	String execute(HttpServletRequest request);

}
