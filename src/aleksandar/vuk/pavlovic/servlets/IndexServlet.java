package aleksandar.vuk.pavlovic.servlets;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation for /Index
 */
public class IndexServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs the servlet instance.
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet()
	{
		super();
	}


	/**
	 * Opens the connection to the server and forwards Index.html
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
		
		ServletContext sc = getServletConfig().getServletContext();

		if (sc.getAttribute("writer") == null || sc.getAttribute("reader") == null)
		{
			try
			{
				final int PORT = 9000;
				Socket sock = new Socket("127.0.0.1", PORT);
				InputStreamReader inputStreamReader = new InputStreamReader(sock.getInputStream());
				BufferedReader reader = new BufferedReader(inputStreamReader);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(sock.getOutputStream());
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				PrintWriter writer = new PrintWriter(bufferedWriter);
	
				sc.setAttribute("sock", sock);
				sc.setAttribute("writer", writer);
				sc.setAttribute("reader", reader);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if (request.getSession().getAttribute("userName") == null)
			request.getRequestDispatcher("Index.html").forward(request, response);
		else
			response.sendRedirect("Main");
	}
}
