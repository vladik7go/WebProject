package by.epam.project.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.project.exception.TechnicalException;

public interface ActionCommand {

	String execute(HttpServletRequest request) ;

}
