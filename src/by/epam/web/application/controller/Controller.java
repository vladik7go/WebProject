package by.epam.web.application.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import by.epam.web.application.commands.ActionCommand;
import by.epam.web.application.commands.factory.ActionFactory;
import by.epam.web.application.pool.ConnectionPool;
import by.epam.web.application.resource.ConfigurationManager;
import by.epam.web.application.resource.MessageManager;

//@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger log = Logger.getLogger(Controller.class);

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		log.debug("init method started (level DEBUG) ----------------------------------------------");
		super.init();
		String log4jConfigPath = ConfigurationManager
				.getProperty("path.log4j.config");
		PropertyConfigurator.configure(log4jConfigPath);
		ConnectionPool.getSinglePool();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet method started--------------------------------------------------------------------------------------------------");
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("doPost method started DEBUG -------------------------------------------------------------------------");
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// заглушка, пока не сделал фильтры
		// request.setCharacterEncoding("UTF-8");
		String page = null;
		// определение команды, пришедшей из JSP
		ActionFactory client = new ActionFactory();
		ActionCommand command = client.defineCommand(request);
		/*
		 * вызов реализованного метода execute() и передача параметров
		 * классу-обработчику конкретной команды
		 */
		page = command.execute(request);
		// метод возвращает страницу ответа
		// page = null; // поэксперементировать!
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(page);
			// вызов страницы ответа на запрос
			dispatcher.forward(request, response);
		} else {
			// установка страницы c cообщением об ошибке
			page = ConfigurationManager.getProperty("path.page.index");
			request.getSession().setAttribute("nullPage",
					MessageManager.getProperty("message.nullpage"));
			response.sendRedirect(request.getContextPath() + page);
		}

	}

	@Override
	public void destroy() {

		super.destroy();
		ConnectionPool.getSinglePool().cleanUpPool();
	}

}
