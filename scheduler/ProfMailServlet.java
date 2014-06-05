package edu.pasadena.scheduler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

//This servlet handles the mailing, it is designed so that the adminstrator can click
//a button which will send all the emails to the professors that will allow them to
//access their accounts.
//Future requirements would require an interface where the admin can
//have a user interface where they can change the hostname server, username, password
//mysql variables
//and other variables that are part of the profMailServlet.
//This servlet needs the Mailer class which does the bulk of the work.
//This servlet also needs some methods that will change the body and subject of the
//message, a slick thing to add would be a method that auto decides the message and subject.
/**
 * Servlet for mailing out email links to the professors
 * @author Gregory Miles, Nick Brooks, Ian Riley
 *
 */
@WebServlet("/ProfMailServlet")
public class ProfMailServlet extends HttpServlet
{
	private Mailer mailer;
	private String to;
	private Database getData;
	
	public void init(){
		
		String hostname = "smtp.gmail.com";
		int port = 587;
		String username = "pccMathSchedule@gmail.com";
		String password = "Super1234Awesome1234Password";
		String from = "pccMathSchedule@gmail.com";
		this.mailer = new Mailer(port,hostname,from,username,password);
		this.to = "gmiles@go.pasadena.edu";
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//String email = request.getParameter("email");
		String sqlUrl = "jdbc:mysql://localhost/scheduler";
		String userName = "schedule_admin";
		String password = "password";
		try {
			getData = new Database(sqlUrl, userName,password);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ArrayList<String> emailList = new ArrayList<String>();
		ArrayList<String> shaSumList = new ArrayList<String>();
		try {
			emailList =  getData.getEmails();
			shaSumList = getData.getSha();
		}  catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		//String subject = request.getParameter("subject");
		//String message = request.getParameter("message");
		String subject = "Class Schedule Email Link";
		String message = "Please click the below link to access your class scheduling form.\n"
				+ "http://localhost:8080/Scheduler/ClassPreferenceServlet?";
		//emailList.add(to);
		String tempMessage = "";
		for(int i = 0; i < emailList.size(); i++)
		{
			this.to = emailList.get(i);
			try{
				mailer.sendEmail(to, subject, message + shaSumList.get(i));
				//	request.setAttribute("message", arg1);
			} catch (Exception e)
			{
				throw new ServletException("Mailer failed", e);
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String queryString = request.getQueryString();
		out.println("<title>Emails Sent</title>" +
				"<body bgcolor=FFFFFF>");
		out.println("<h2>Success! Sent all emails.</h2>");
		out.println("<p>Return to <a href=\"admin.jsp\">Form</a>");

		out.close();
	}
}
