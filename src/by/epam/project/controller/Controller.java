package by.epam.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import by.epam.project.command.ActionCommand;
import by.epam.project.command.factory.ActionFactory;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.resource.ConfigurationManager;
import by.epam.project.resource.MessageManager;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger log = Logger.getLogger(Controller.class);

	public Controller() {
		super();
		
	}

	@Override
	public void init() throws ServletException {
		
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
		// substitution, unless create filters
		// request.setCharacterEncoding("UTF-8");
		String page = null;
		// Define the command received from JSP
		ActionFactory client = new ActionFactory();
		ActionCommand command = client.defineCommand(request);
		/*
		 * 
		 * Call embodied method execute() and send parameters to the
		 * class-handler of concrete command
		 */
		page = command.execute(request);
		// this method return the page due to the sent command 
		
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(page);
			// Call the page of the answer, according to the request 
			dispatcher.forward(request, response);
		} else {
			// Call the index page and set the attribute - "doesn't exist page" 
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
