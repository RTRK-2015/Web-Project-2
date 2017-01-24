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

import aleksandar.vuk.pavlovic.model.MailToServer;


/**
 * Servlet implementation class /WritingMail
 */
public class WritingMailServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs a servlet instance.
	 * @see HttpServlet#HttpServlet()
	 */
	public WritingMailServlet()
	{
		super();
	}


	/**
	 * Forwards the request to the jsp page.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("WritingMail.jsp").forward(request, response);
	}


	/**
	 * Responds to a POST request (AJAX from the page) by sending a request to the server to "send" mail.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		final String to = request.getParameter("mailto");
		final String subject = request.getParameter("mailsubject");
		final String body = request.getParameter("mailbody");
		final String from = (String)request.getSession().getAttribute("userName");
		final MailToServer mail = new MailToServer(from, to, subject, body);
		final String mailJSON = new Gson().toJson(mail, MailToServer.class);
		
		ServletContext sc = getServletContext();
		
		PrintWriter writer = (PrintWriter) sc.getAttribute("writer");
		BufferedReader reader = (BufferedReader) sc.getAttribute("reader");

		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("command", "SEND");
		requestMap.put("mail", mailJSON);
		
		final String requestJSON = new Gson().toJson(requestMap, Map.class);
		writer.println(requestJSON);
		writer.flush();

		while (!reader.ready())
			;
		final String responseJSON = reader.readLine();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(responseJSON);
	}
}
