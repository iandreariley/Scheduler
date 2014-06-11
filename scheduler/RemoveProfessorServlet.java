package edu.pasadena.scheduler;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveProfessorServlet
 */
@WebServlet("/RemoveProfessorServlet")
public class RemoveProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_URL = "jdbc:mysql://localhost:3306/scheduler";
	private static final String USER_NAME = "schedule_admin";
	private static final String PASSWORD = "password";
       

	/**
	 * Method attempts to remove an instructor from database using supplied id.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		//default message - database connection error
		String responseMessage = "Database access error, instructor not removed.";
		
		try
		{
			//access database and attempt to remove instructor
			//edit message to reflect success or failure.
			Database db = new Database(DB_URL, USER_NAME, PASSWORD);
			if(db.removeInstructor(id))
				responseMessage = "Instructor Removed";
			else
				responseMessage = "Invalid id. Instructor not removed.";
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
		
		//set request message
		request.setAttribute("message", responseMessage);
		
		//attempt to forward message to admin.jsp
		try
		{
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		catch(ServletException se)
		{
			se.printStackTrace();
		}
	}
}
