package aleksandar.vuk.pavlovic.filters;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Checks whether the user must be logged in to access a particular page and
 * redirects accordingly.
 */
public class LoggedInFilter implements Filter
{
	/**
	 * Performs the actual filtering, not letting the user parts of the site that require them
	 * to be logged in.
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		final String uri = httpRequest.getRequestURI();
		final String[] parts = uri.split("/");
		// if the user is not logged in, redirect to login page
		if (httpRequest.getSession().getAttribute("userName") != null ||
				parts[parts.length - 1].equals("Index") ||
				parts[parts.length - 1].equals("Register") ||
				parts[parts.length - 1].equals("RegisterFailed") ||
				(parts[parts.length - 1].equals("Main") && httpRequest.getMethod().equals("POST")))
		{
			// Move along, nothing to see here
			chain.doFilter(request, response);		
		}
		else
		{
			// Hold it, chief
			((HttpServletResponse)response).sendRedirect("Index");
		}
	}


	/**
	 * Does literally nothing.
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
	}


	/**
	 * @see LoggedInFilter#init(FilterConfig)
	 */
	@Override
	public void destroy()
	{	
	}
}
