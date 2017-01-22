package aleksandar.vuk.pavlovic.servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import aleksandar.vuk.pavlovic.model.MailFromServer;


/**
 * Servlet implementation class ReadingMailServlet
 */
public class ReadingMailServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadingMailServlet()
	{
		super();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		RequestDispatcher dispatcher = request.getRequestDispatcher("ReadingMail.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("idnum"));
		
		ServletContext sc = getServletContext();
		
		PrintWriter writer = (PrintWriter) sc.getAttribute("writer");
		BufferedReader reader = (BufferedReader) sc.getAttribute("reader");

		String userName = request.getParameter("username");
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("command", "RECEIVE");
		requestMap.put("id", id);
		String requestJSON = new Gson().toJson(requestMap, Map.class);
		writer.println(requestJSON);
		writer.flush();

		while (!reader.ready())
			;
		String responseJSON = reader.readLine();
		@SuppressWarnings("unchecked")
		Map<String, Object> responseMap = new Gson().fromJson(responseJSON, Map.class);

		Map<String, Object> ajaxResponseMap = new HashMap<>();
		
		if ((boolean) responseMap.get("success"))
		{
			MailFromServer mail = new Gson().fromJson((String)responseMap.get("mail"), MailFromServer.class);
			
			ajaxResponseMap.put("success", true);
			ajaxResponseMap.put("from", mail.from);
			ajaxResponseMap.put("subject", mail.subject);
			ajaxResponseMap.put("body", mail.body);
		}
		else
		{
			ajaxResponseMap.put("success", false);
			ajaxResponseMap.put("error", "Could not find that mail!");
		}
		
		String ajaxResponseJSON = new Gson().toJson(ajaxResponseMap, Map.class);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(ajaxResponseJSON);
	}
}
