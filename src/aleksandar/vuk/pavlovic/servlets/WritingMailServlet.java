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

import aleksandar.vuk.pavlovic.model.MailToServer;


/**
 * Servlet implementation class WritingMailServlet
 */
public class WritingMailServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WritingMailServlet()
	{
		super();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("WritingMail.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Map<String, String[]> map = request.getParameterMap();
		
		String to = request.getParameter("mailto");
		String subject = request.getParameter("mailsubject");
		String body = request.getParameter("mailbody");
		String from = (String)request.getSession().getAttribute("userName");
		MailToServer mail = new MailToServer(from, to, subject, body);
		String mailJSON = new Gson().toJson(mail, MailToServer.class);
		
		ServletContext sc = getServletContext();
		
		PrintWriter writer = (PrintWriter) sc.getAttribute("writer");
		BufferedReader reader = (BufferedReader) sc.getAttribute("reader");

		String userName = request.getParameter("username");
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("command", "SEND");
		requestMap.put("mail", mailJSON);
		
		String requestJSON = new Gson().toJson(requestMap, Map.class);
		writer.println(requestJSON);
		writer.flush();

		while (!reader.ready())
			;
		String responseJSON = reader.readLine();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(responseJSON);
	}

}
