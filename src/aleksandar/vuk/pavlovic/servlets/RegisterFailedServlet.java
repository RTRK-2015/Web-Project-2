package aleksandar.vuk.pavlovic.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation for /RegisterFailed
 */
public class RegisterFailedServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs a servlet instance.
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterFailedServlet()
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
		request.getRequestDispatcher("RegisterFailed.html").forward(request, response);
	}

}
