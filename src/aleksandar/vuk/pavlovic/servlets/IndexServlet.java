package aleksandar.vuk.pavlovic.servlets;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final int PORT = 9000;


	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		ServletContext cs = config.getServletContext();
		
		if (cs.getAttribute("writer") == null || cs.getAttribute("reader") == null)
		{
			try
			{
				Socket sock = new Socket("127.0.0.1", PORT);
				InputStreamReader inputStreamReader = new InputStreamReader(sock.getInputStream());
				BufferedReader reader = new BufferedReader(inputStreamReader);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(sock.getOutputStream());
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				PrintWriter writer = new PrintWriter(bufferedWriter);
	
				cs.setAttribute("writer", writer);
				cs.setAttribute("reader", reader);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet()
	{
		super();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("Index.html");
		dispatcher.forward(request, response);
	}
}
