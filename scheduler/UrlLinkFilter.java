package edu.pasadena.scheduler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 
 * @author Gregory Miles, Ian Riley, Nick Brooks
 * This servlet is the first servlet encountered and the filter which
 * handles what comes traffic is able to access the other servlets and files.
 */
@WebFilter("/UrlLinkFilter")
public class UrlLinkFilter implements Filter
{

	private Database testData;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//Initialize the database class.
		String sqlUrl = "jdbc:mysql://localhost/javabase";
		String userName = "java";
		String password = "password";
		try {
			testData = new Database(sqlUrl,userName,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Cast the ServleRequest and ServletResponse as HttpServletRequest and response
		//so that the methods of HttpServletRequest can be accessed to
		//get the query string.
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		//String url = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		String requestURI = request.getRequestURI();
		
		//Query string should be a shasum which is then checked in the
		//database.
		boolean isQuery = false;
		try {
			isQuery = testData.shaSumExists(queryString);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//shaSum shaTest = new shaSum();
		
		if(queryString != null)
		{
			//If the query is in there then link is OK.
			//if(queryString.matches("gregory"))
			if(isQuery)
			{
				//arg2.doFilter(arg0, arg1);
				//if(requestURI.startsWith("/mySQLtest/"))
				//{
					int secondSlashPos = requestURI.indexOf('/',requestURI.indexOf('/')+1);
					
					//requestURI.replaceFirst("mySQLtest", "");
					String newURI = requestURI.substring(secondSlashPos) + "?" + queryString;
					//String newURI =  "ServletDemo1?name=";
					//String newURL = url + "?name=";
					//response.sendRedirect(newURL);
					arg0.getRequestDispatcher(newURI).forward(arg0, arg1);
				//}
				//else
				//{
				//	arg2.doFilter(arg0, arg1);
				//}
			}
		}else
		{
			response.sendRedirect("admin.jsp");

		}
		
		//Otherwise forward the page to the admins.
		
		//response.sendRedirect("admin.html");
		/*HttpServletResponse httpResponse = (HttpServletResponse) arg1;
		
		if(queryString != null)
		{
			httpResponse.getWriter().write("<p>" + shaTest.toSha256(queryString.getBytes()) + "</p>");
		}
		httpResponse.getWriter().write("<p>" + url + "</p>");*/
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}