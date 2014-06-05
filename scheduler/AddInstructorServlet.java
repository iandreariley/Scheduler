package edu.pasadena.scheduler;

import java.sql.SQLException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AddInstructorServlet")
public class AddInstructorServlet extends HttpServlet{

	private static final String DB_URL = "jdbc:mysql://localhost:3306/scheduler";
	private static final String USER_NAME = "schedule_admin";
	private static final String PASSWORD = "password";
	
	private Database data;
	
	public AddInstructorServlet()
	{
		super();
		try
		{
			data = new Database(DB_URL, USER_NAME, PASSWORD);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		boolean fullTime = request.getParameter("full_time").equals("1") ? true : false;
		String responseMessage = "";
		String validID = "[0-9]{8}";
		boolean duplicate = false;
		
		//check if the id exists
		try{
			duplicate = data.idExists(id);
		}
		catch(SQLException sql){
			sql.printStackTrace();
			return;
		}catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
			return;
		}
		
		if(id.matches(validID) && !duplicate)
		{
			//make a new id!
			String query = "first="+firstName+"&last="+lastName+"&id="+id;
			try{
				data.addInstructor(id, firstName + " " + lastName, fullTime, email, "", query);
			} catch (SQLException sql){
				sql.printStackTrace();
			} catch (ClassNotFoundException cnf){
				cnf.printStackTrace();
			}
			responseMessage = "Instructor added";
		}
		else
		{
			//inform page that there was an issue
			responseMessage = "Id is invalid or is already in use - please enter a new id";
		}
		
		request.setAttribute("message", responseMessage);
		try{
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}catch(IOException io){
			io.printStackTrace();
		}catch(ServletException servlet){
			servlet.printStackTrace();
		}
	}
}