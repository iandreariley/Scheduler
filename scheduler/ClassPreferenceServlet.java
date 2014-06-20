package edu.pasadena.scheduler;
import java.sql.SQLException;
import java.io.IOException;

//This allows the annotation @webservlet to specify servlet mapping w/out xml
//xml must reflect proper servlet mapping for actual deployment
import javax.servlet.annotation.WebServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//I used tomcat8 and I had to put the jdbc in the correct folder
//Which is the lib folder of the server or $CATALINA_HOME/lib
//This file went into the folder structure 
//$CATALINA_HOME/webapps/mySQLtest/WEB-INF/classes/com/servletTest/
//you do this after compiling the project.
//Also you need to add the servlet-api.jar from tomcat 8 lib file 
//to the classpath of eclipse project for this file in order
//to compile it.


/**
 * 
 * @author Nicholas Brooks, Gregory Miles, Ian Riley
 *
 */
@WebServlet("/ClassPreferenceServlet") //accomplishes servlet mapping w/out xml
public class ClassPreferenceServlet extends HttpServlet{
	
	private final static String url = "jdbc:mysql://localhost:3306/scheduler";
	private final static String username = "schedule_admin";
	private final static String password = "password";
	
	
	public void doPost(HttpServletRequest request, 
	         HttpServletResponse response)
	        throws ServletException, IOException
	  {
//      PrintWriter out = response.getWriter();                                               

        //if statements determine which page the post request is coming from                  
        if(request.getParameter("first_name") != null)
        {
            response.setContentType("text/html");
            String name = request.getParameter("first_name") + " " + request.getParameter("last_name");
           		   
            String id = request.getParameter("id");
            String fc = request.getParameter("first_choice");
            String fcDays = request.getParameter("first_choice_days");
            String fcTime = request.getParameter("first_choice_time");
            String sc = request.getParameter("second_choice");
            String scDays = request.getParameter("second_choice_days");
            String scTime = request.getParameter("second_choice_time");
            String tc = "";
            String tcDays = "";
            String tcTime = "";
            if(request.getParameter("fulltime").equals("1"))
            {
             	tc = request.getParameter("third_choice");
              	tcTime = request.getParameter("third_choice_time");
               	tcDays = request.getParameter("third_choice_days");
            }

            try
            {
                Database dat = new Database(url, username, password);
                dat.addForm(id, name, fc, fcDays, fcTime, sc, scDays, scTime, tc, tcDays, tcTime, "");
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            catch(ClassNotFoundException e)
            {
               System.out.println("Class not found");
               e.printStackTrace();
            }

        }
        RequestDispatcher rd = request.getRequestDispatcher("submit.jsp");
        
        try
        {
        	rd.forward(request,  response);
        }
        catch(IOException exp)
        {
        	exp.printStackTrace();
        }
	  }
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		response.setContentType("text/html");
		
		RequestDispatcher rd = request.getRequestDispatcher("submit.jsp");
		
		try
		{
			rd.forward(request, response);
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
	}
	
}