package by.epam.web.application.commands;

import javax.servlet.http.HttpServletRequest;

import by.epam.web.application.exceptions.TechnicalException;

public interface ActionCommand {

	String execute(HttpServletRequest request) throws TechnicalException ;

}
