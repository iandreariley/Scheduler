package edu.pasadena.scheduler;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet{
	
	private Database database;
	
	private final static String url = "jdbc:mysql://localhost:3306/scheduler";
	private final static String username = "schedule_admin";
	private final static String password = "password";
	
	public DownloadServlet()
	{
		//call super-class constructor
		super();
		
		//attempt to create a database object
		try
		{
			database = new Database(url, username, password);
		}
		catch(SQLException e)
		{
			System.out.println("Failed to access database at '" + url + "'\n");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Driver.class not found.\n");
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

		//set type of file
	    response.setContentType("text/html");
    	try
    	{
    		//Stream csv to client
    		String fileName = "dreamSheetCSV";
    		response.setContentType("text/plain charset=utf-8");
    		ServletOutputStream downloadStream = response.getOutputStream();
    		if(downloadStream.equals(null))
    		{
    			Logger.getGlobal().info("downloadStream is null");
    		}
    		else
    		{
	    		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	    		downloadStream.write(database.formsToCSV().getBytes(Charset.forName("UTF-8")));
	    		downloadStream.flush();
	    		downloadStream.close();
    		}
    	}
    	catch(SQLException exp)
    	{
    		exp.printStackTrace();
    	}
    	catch(ClassNotFoundException cnf)
    	{
    		cnf.printStackTrace();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{

		
		RequestDispatcher rd = request.getRequestDispatcher("admin.html");
		
		try
		{
			rd.forward(request, response);
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
		catch(ServletException exp)
		{
			exp.printStackTrace();
		}
	}

}
