package aleksandar.vuk.pavlovic.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation for /RegisterFailedLogged
 */
public class RegisterFailedLoggedServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs a servlet instance.
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterFailedLoggedServlet()
	{
		super();
	}


	/**
	 * Forwards the request to the html page.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("RegisterFailedLogged.html").forward(request, response);
	}
}
