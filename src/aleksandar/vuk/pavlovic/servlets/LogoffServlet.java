package aleksandar.vuk.pavlovic.servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


/**
 * Servlet implementation for /Logoff
 */
public class LogoffServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs the servlet instance.
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoffServlet()
	{
		super();
	}


	/**
	 * Sends the logoff request to mail server before returning the user to Index.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletContext sc = getServletContext();
		
		PrintWriter writer = (PrintWriter) sc.getAttribute("writer");

		final String userName = request.getParameter("username");
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("command", "LOGOFF");
		requestMap.put("userName", userName);
		final String requestJSON = new Gson().toJson(requestMap, Map.class);
		writer.println(requestJSON);
		writer.flush();

		Socket sock = (Socket)sc.getAttribute("sock");
		BufferedReader reader = (BufferedReader)sc.getAttribute("reader");
		
		writer.close();
		reader.close();
		sock.close();
		sc.setAttribute("sock", null);
		sc.setAttribute("writer", null);
		sc.setAttribute("reader", null);
		
		request.getSession().invalidate();
		response.sendRedirect("Index");
	}
}
