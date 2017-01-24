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
 * Servlet implementation for /Register
 */
public class RegisterServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs a servlet instance.
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet()
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
		request.getRequestDispatcher("Register.html").forward(request, response);
	}


	/**
	 * Responds to a POST request by sending a request to the server to register the user.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getSession().getAttribute("username") != null)
		{
			response.sendRedirect("RegisterFailedLogged");
		}
		else
		{
			ServletContext sc = getServletContext();
			
			PrintWriter writer = (PrintWriter) sc.getAttribute("writer");
			BufferedReader reader = (BufferedReader) sc.getAttribute("reader");
	
			final String userName = request.getParameter("username");
			Map<String, Object> requestMap = new HashMap<>();
			requestMap.put("command", "REGISTER");
			requestMap.put("userName", userName);
			final String requestJSON = new Gson().toJson(requestMap, Map.class);
			writer.println(requestJSON);
			writer.flush();
	
			while (!reader.ready())
				;
			final String responseJSON = reader.readLine();
			@SuppressWarnings("unchecked")
			final Map<String, Object> responseMap = new Gson().fromJson(responseJSON, Map.class);
	
			if ((boolean) responseMap.get("success"))
			{
				request.getSession().setAttribute("userName", userName);
				response.sendRedirect("Index");
			}
			else
			{
				response.sendRedirect("RegisterFailed");
			}
		}
	}
}
