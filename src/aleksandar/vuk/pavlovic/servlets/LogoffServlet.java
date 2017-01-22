package aleksandar.vuk.pavlovic.servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


/**
 * Servlet implementation class LogoffServlet
 */
public class LogoffServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoffServlet()
	{
		super();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletContext sc = getServletContext();
		
		PrintWriter writer = (PrintWriter) sc.getAttribute("writer");

		String userName = request.getParameter("username");
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("command", "LOGOFF");
		requestMap.put("userName", userName);
		String requestJSON = new Gson().toJson(requestMap, Map.class);
		writer.println(requestJSON);
		writer.flush();

		request.getSession().invalidate();
		response.sendRedirect("Index");
	}

}
