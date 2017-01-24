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

import aleksandar.vuk.pavlovic.model.MailFromServer;


/**
 * Servlet implementation for /ReadingMail
 */
public class ReadingMailServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs a servlet instance.
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadingMailServlet()
	{
		super();
	}


	/**
	 * Responds to a GET request by forwarding to the page.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		request.getRequestDispatcher("ReadingMail.jsp").forward(request, response);
	}
	
	
	/**
	 * Responds to a POST request (sent by AJAX on the page) by loading the specified mail from the server.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		final int id = Integer.parseInt(request.getParameter("mailid"));
		
		ServletContext sc = getServletContext();
		
		PrintWriter writer = (PrintWriter) sc.getAttribute("writer");
		BufferedReader reader = (BufferedReader) sc.getAttribute("reader");

		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("command", "RECEIVE");
		requestMap.put("id", id);
		final String requestJSON = new Gson().toJson(requestMap, Map.class);
		writer.println(requestJSON);
		writer.flush();

		while (!reader.ready())
			;
		final String responseJSON = reader.readLine();
		@SuppressWarnings("unchecked")
		final Map<String, Object> responseMap = new Gson().fromJson(responseJSON, Map.class);

		Map<String, Object> ajaxResponseMap = new HashMap<>();
		
		if ((boolean) responseMap.get("success"))
		{
			final MailFromServer mail = new Gson().fromJson((String)responseMap.get("mail"), MailFromServer.class);
			
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
		
		final String ajaxResponseJSON = new Gson().toJson(ajaxResponseMap, Map.class);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(ajaxResponseJSON);
	}
}
