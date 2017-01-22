package aleksandar.vuk.pavlovic.servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane.ScalableIconUIResource;

import aleksandar.vuk.pavlovic.model.MailSnippet;


/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet()
	{
		super();
	}


	private void loadMails(HttpServletRequest request) throws IOException
	{
		ServletContext sc = getServletContext();
		
		PrintWriter writer = (PrintWriter) sc.getAttribute("writer");
		BufferedReader reader = (BufferedReader) sc.getAttribute("reader");
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("command", "LIST");
		String requestJSON = new Gson().toJson(requestMap, Map.class);
		
		writer.println(requestJSON);
		writer.flush();
		
		while (!reader.ready());
		String responseJSON = reader.readLine();
		@SuppressWarnings("unchecked")
		Map<String, Object> responseMap = new Gson().fromJson(responseJSON, Map.class);
		@SuppressWarnings("unchecked")
		Collection<String> collectionJSON = (Collection<String>)responseMap.get("mails");
		ArrayList<MailSnippet> snippets = new ArrayList<>();
		
		for (String snippetJSON : collectionJSON)
			snippets.add(new Gson().fromJson(snippetJSON, MailSnippet.class));
		
		HttpSession session = request.getSession();
		session.setAttribute("mails", snippets);
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getSession().getAttribute("userName") != null)
		{
			loadMails(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			response.sendRedirect("Index");
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletContext sc = getServletContext();
		
		PrintWriter writer = (PrintWriter) sc.getAttribute("writer");
		BufferedReader reader = (BufferedReader) sc.getAttribute("reader");

		String userName = request.getParameter("username");
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("command", "LOGIN");
		requestMap.put("userName", userName);
		String requestJSON = new Gson().toJson(requestMap, Map.class);
		writer.println(requestJSON);
		writer.flush();

		while (!reader.ready())
			;
		String responseJSON = reader.readLine();
		@SuppressWarnings("unchecked")
		Map<String, Object> responseMap = new Gson().fromJson(responseJSON, Map.class);

		if ((boolean) responseMap.get("success"))
		{
			request.getSession().setAttribute("userName", userName);
			response.sendRedirect("Main");
		}
		else
		{
			response.sendRedirect("Index");
		}
	}

}
