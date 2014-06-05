package edu.pasadena.scheduler;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.*;
import java.util.ArrayList;

 /**
  * An object of this class manipulates data and makes queries to the database specified
  * in the 'url' variable.
  * @author Nicholas Brooks, Gregory Miles, Ian Riley
  *
  */
public class Database {
	
	//test shaSum - 64 char
	private final static String SHA = "abcdefghijklmnopqrstuvwxyz123456abcdefghijklmnopqrstuvwxyz123456";
	
	//Requisite tables
	private final static String[] TABLES = {"instructor", "course", "schedule", "requestForm", "choice", "user"};
	
	//CREATE TABLE parameters for each table
	private final static String USER_COLUMNS = "(user_name VARCHAR(20) PRIMARY KEY, password VARCHAR(20))";
	private final static String CHOICE_COLUMNS = "(id INTEGER(8) ZEROFILL NOT NULL, course_name VARCHAR(10) NOT NULL,"
			+ "days VARCHAR(6) NOT NULL, time VARCHAR(11) NOT NULL, choice_number TINYINT(1) NOT NULL)";
	private final static String COURSE_COLUMNS = "(course_id VARCHAR(10) PRIMARY KEY, course_name VARCHAR(15),"
			+ "description VARCHAR(500))";
	private final static String INSTRUCTOR_COLUMNS = "(id INTEGER(8) ZEROFILL PRIMARY KEY, name VARCHAR(40),"
			+ " full_time BOOLEAN, email VARCHAR(60), notes VARCHAR(250), sha_sum CHAR(64))";
	private final static String SCHEDULE_COLUMNS = "(crn VARCHAR(4), course_name VARCHAR(10), days VARCHAR(6),"
			+ " hours VARCHAR(11), instructor VARCHAR(20), instructor_id INTEGER(8) ZEROFILL, session TINYINT, notes VARCHAR(250),"
			+ " PRIMARY KEY(crn))";
	private final static String FORM_COLUMNS = "(id INTEGER(8) ZEROFILL PRIMARY KEY, name VARCHAR(40), notes VARCHAR(250))";
	
	private String url;
	private String user;
	private String password;
	
	Connection connection;
	
	/**
	 * Creates an object that can access, query and manipulate a database inside the server.
	 * 
	 * @param url The url of the database
	 * @param dbName The name of the database inside mysql
	 * @param user The user name used to access the database. Must have ALL permissions.
	 * @param host The host of the database
	 * @param password password associated with user in mysql database ('user' IDENTIFIED BY 'pw');
	 * @throws SQLException
	 */
	public Database(String url, String user, String password) throws SQLException, ClassNotFoundException
	{
		this.url = url;
		this.user = user;
		this.password = password;
		
		//open connection
		openConnection();
		
		createNecessaryTables();
		
		//close connection
		connection.close();
	}
	
	/**
	 * Attempts to add a user row to the user table
	 * @param userName the user's username. must be unique.
	 * @param pw the user's password
	 * @return true if the row was successfully added, false otherwise (duplicate username)
	 * @throws SQLException if there is a database access issue
	 * @throws ClassNotFoundException if Driver.class isn't found in the openConnection() method
	 */
	public Boolean addUser(String userName, String pw) throws SQLException, ClassNotFoundException
	{
		openConnection();
		Statement statement = connection.createStatement();
		boolean success = true;
		try
		{
			statement.executeUpdate("INSERT INTO user VALUES('" + userName + "', '" + pw + "')");
		}
		catch(MySQLIntegrityConstraintViolationException duplicateUserName)
		{
			success = false;
		}
		
		statement.close();
		connection.close();
		
		return success;
	}
	
	/**
	 * Checks whether a certain id/password combination is in the 'user' table in the database
	 * @param userName login id
	 * @param pw password
	 * @return true if the user is found in the database 'user' table, false otherwise
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Boolean validateUser(String userName, String pw) throws SQLException, ClassNotFoundException
	{
		openConnection();
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE user_name='" + userName + "' AND"
				+ " password='" + pw + "'" );
		
		if(rs.next())
			return true;
		
		return false;
	}
	
	/**
	 * Attempts to add a row to the professor table, with the value of 'id' in the id columns,
	 * and null values in all other columns.
	 * @param id integer of 8 or fewer digits
	 * @return true if the row was successfully added, false if the id provided duplicates
	 * an id already in the table, and a row was not added to the table
	 * @throws SQLException
	 */
	public boolean addInstructor(String id, String name, boolean fullTime, String email, String remarks, String shaSum) throws SQLException, ClassNotFoundException
	{
		//open statement and connection
		openConnection();
		Statement statement = connection.createStatement();
		boolean success = true;
		//sets full time to an sql boolean value string (1 or 0)
		String ft = fullTime ? "1" : "0";
		try
		{
			//will throw exception if 'id' value already exists in instructor table
			statement.executeUpdate("INSERT INTO instructor VALUES(" + id + ", '" + name + "', " + ft + 
					", '" + email + "', '" + remarks + "', '" + shaSum + "')");
		}
		catch(MySQLIntegrityConstraintViolationException sqlException)
		{
			//returns false in the case that the 'id' string is a duplicate
			success = false;
		}
		finally
		{
			//close statement and connection
			statement.close();
			connection.close();
		}
		return success;
	}
	
	/**
	 * Attempts to remove an instructor row from the instructor table
	 * @param id the unique id number of the instructor to be removed
	 * @return true if rows were deleted, false if not.
	 * @throws SQLException if there is a database connection exception
	 */
	public boolean removeInstructor(String id) throws SQLException, ClassNotFoundException
	{
		openConnection();
		Statement statement = connection.createStatement();
		//issues delete command, and gets number of affected rows
		int affectedRows = statement.executeUpdate("DELETE FROM instructor WHERE id=" + id);
		
		//close statement and connection
		statement.close();
		connection.close();
		
		//returns true if rows were deleted false otherwise
		return affectedRows != 0;
	}
	
	/**
	 * Attempts to add an instructor form to the Form table, and choices to the choice table
	 * with the arguments as column values
	 * @param id instructor's unique id number, cannot be repeated
	 * @param name instructor's full name
	 * @param fc first choice class
	 * @param fcDays first choice class days
	 * @param fcTime first choice time block
	 * @param sc second choice class
	 * @param scDays second choice days
	 * @param scTime second choice time block
	 * @param tc
	 * @param tcDays
	 * @param tcTime
	 * @param notes additional notes on request
	 * @return true if addition is successful, false if the id field duplicates a value in the database
	 * @throws SQLException if there is an access problem
	 */
	public boolean addForm(String id, String name, String fc, String fcDays, String fcTime,
							String sc, String scDays, String scTime, String tc, String tcDays,
							String tcTime, String notes) throws SQLException, ClassNotFoundException
	{
		openConnection();
		Statement statement = connection.createStatement();
		String dlm = "', '";
		boolean success = true;
		boolean overWrite = idExists(id);
		
		try
		{
			//overwriting a previously written row in database
			if(overWrite)
			{
				statement.executeUpdate("UPDATE choice SET course_name='" + fc + "', days='" + fcDays + "', "
						+ "time='" + fcTime + "' WHERE id=" + id + " AND choice_number=1");
				statement.executeUpdate("UPDATE choice SET course_name='" + sc + "', days='" + scDays + "', "
						+ "time='" + scTime + "' WHERE id=" + id + " AND choice_number=2");
				if(tc != "") //if the instructor has a third choice
					statement.executeUpdate("UPDATE choice SET course_name='" + tc + "', days='" + tcDays + "', "
							+ "time='" + tcTime + "' WHERE id=" + id + " AND choice_number=3");
			}
			else
			{
				statement.executeUpdate("INSERT INTO requestForm VALUES(" + id + ", '" + name + dlm + notes + "')");
				statement.executeUpdate("INSERT INTO choice VALUES(" + id + ", '" + fc + dlm + fcDays + dlm + fcTime + "', 1)");
				statement.executeUpdate("INSERT INTO choice VALUES(" + id + ", '" + sc + dlm + scDays + dlm + scTime + "', 2)");
				if(tc != "") //if the instructor has a third choice
					statement.executeUpdate("INSERT INTO choice VALUES(" + id + ", '" + tc + dlm + tcDays + dlm + tcTime + "', 3)");
			}
		}
		catch(MySQLIntegrityConstraintViolationException sqlException)
		{
			success = false;
		}
		finally
		{
			statement.close();
			connection.close();
		}
		
		return success;
	}
	
	/**
	 * 
	 * @param id id number of the instructor whose form is to be deleted
	 * @return true if row was found and deleted, false if row with that instructor id does not exist
	 * @throws SQLException if there is a database access problem
	 */
	public boolean removeForm(String id) throws SQLException, ClassNotFoundException
	{
		openConnection();
		Statement statement = connection.createStatement();
		//delete form entry and choice entries
		int affectedRows = statement.executeUpdate("DELETE FROM requestForm WHERE id=" + id);
		statement.executeUpdate("DELETE FROM choice WHERE id=" + id);
		
		statement.close();
		connection.close();
		
		return affectedRows != 0;
	}
	
	/**
	 * Attempts to load a tab-delimited text file of data into instructor table
	 * @param filePath
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean loadInstructorData(String filePath) throws SQLException, ClassNotFoundException
	{
		openConnection();
		Statement statement = connection.createStatement();
		int affectedRows = statement.executeUpdate("LOAD DATA LOCAL INFILE '" + filePath + "' INTO TABLE instructor");
		
		statement.close();
		connection.close();
		
		return affectedRows != 0;
	}
	
	/**
	 * Attempts to get an email address from database based on a name
	 * @param name the name of the instructor whose id is required
	 * @return zero strings if the name is not found, one if only one occurance is found
	 * multiple id strings if multiple instances of the name are found.
	 * @throws SQLException on access errors
	 * @throws ClassNotFoundException on Driver.class not found in WEB-INF folder
	 */
	public ArrayList<String> getIdFromName(String name) throws SQLException, ClassNotFoundException
	{
		ArrayList<String> ids = new ArrayList<String>();
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT id FROM instructor WHERE name='" + name + "'");
		while(rs.next())
			ids.add(rs.getString(1));

		statement.close();
		connection.close();
		
		return ids;
	}
	
	/**
	 * Attempts to get an email address from database based on an id number
	 * @param id the id number of the instructor whose email is required
	 * @return email string if instructor id is found, empty string otherwise
	 * @throws SQLException on database access exceptions
	 * @throws ClassNotFoundException on Driver.class not being found
	 */
	public String getEmail(String id) throws SQLException, ClassNotFoundException
	{
		String email = "";
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT email FROM instructor WHERE id=" + id);
		if(rs.next())
			email += rs.getString(1);
		
		statement.close();
		connection.close();
		
		return email;
	}
	
	/**
	 * Creates a string representing the values of a table in the database in comma-separated value format.
	 * Rows are terminated with the new-line character. The table has five columns: course_name, instructor
	 * name, instructor id, choice number, notes
	 * @return Values of table in string format
	 * @throws SQLException upon database access problems
	 */
	public String formsToCSV() throws SQLException, ClassNotFoundException
	{
		String table = "";
		//delimiter
		String dlm = ",";
		
		openConnection();
		Statement statement = connection.createStatement();
		
		//query db - returns |course_name VARCHAR()|instr_name VARCHAR()|id# INT|choice TINYINT|notes VARCHAR()
		ResultSet rs = statement.executeQuery("SELECT choice.course_name, requestForm.name, choice.id,"
				+ " choice.choice_number, requestForm.notes FROM choice INNER JOIN requestForm"
				+ " ON choice.id=requestForm.id ORDER BY choice.course_name");
		
		//parse table into comma-separated values
		while(rs.next())
		{
			//adds row to table string
			table += rs.getString(1) + dlm + rs.getString(2) + dlm + rs.getInt(3) + dlm + rs.getInt(4)
			+ dlm + rs.getString(5) + "\n";
		}
		
		//close statement and connection
		statement.close();
		connection.close();
		
		return table;
	}
	
	/**
	 * Returns a name based on a sha sum argument
	 * @param shaSum
	 * @return the name associated with that sha sum in the instructor table in the database
	 * @throws SQLException on access exception
	 * @throws ClassNotFoundException if Driver.class is not found in Tomcat WEB-INF folder
	 */
	public String getName(String shaSum) throws SQLException, ClassNotFoundException
	{
		String name = "";
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT name FROM instructor WHERE sha_sum='" + shaSum + "'");
		if(rs.next())
			name = rs.getString(1);

		//close connection
		statement.close();
		connection.close();
		
		return name;
	}
	
	/**
	 * Gets an id based on a sha sum argument
	 * @param shaSum the sha sum associated with the id
	 * @return an id in String form if the sha sum is found, empty string otherwise
	 * @throws SQLException on database access exception
	 * @throws ClassNotFoundException If Driver.class is not found in tomcat WEB-INF folder
	 */
	public String getIdFromSha(String shaSum) throws SQLException, ClassNotFoundException
	{
		String id = "";
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT id FROM instructor WHERE sha_sum='" + shaSum + "'");
		if(rs.next())
			id = rs.getString(1);

		statement.close();
		connection.close();
		
		return id;
	}
	
	/**
	 * Gets a list of names from database instructor table
	 * @return ArrayList of names as Strings
	 * @throws SQLException on db access exception
	 * @throws ClassNotFoundException when Driver.class is not found in tomcat WEB-INF folder
	 */
	public ArrayList<String> getNames() throws SQLException, ClassNotFoundException
	{
		ArrayList<String> names = new ArrayList<String>();
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT name FROM instructor");
		while(rs.next())
			names.add(rs.getString(1));

		statement.close();
		connection.close();
		
		return names;
	}
	
	/**
	 * Gets a list of sha sums from instructor table in database
	 * @return ArrayList of sha sums as strings
	 * @throws SQLException on db access exception
	 * @throws ClassNotFoundException when Driver.class is not found in tomcat WEB-INF folder
	 */
	public ArrayList<String> getSha() throws SQLException, ClassNotFoundException
	{
		ArrayList<String> shas = new ArrayList<String>();
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT sha_sum FROM instructor");
		while(rs.next())
			shas.add(rs.getString(1));

		statement.close();
		connection.close();
		
		return shas;
	}
	
	/**
	 * Gets a list of ids from instructor table in database
	 * @return ArrayList of ids as Strings
	 * @throws SQLException on db access exception
	 * @throws ClassNotFoundException when Driver.class is not found in tomcat WEB-INF folder
	 */
	public ArrayList<String> getIds() throws SQLException, ClassNotFoundException
	{
		ArrayList<String> ids = new ArrayList<String>();
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT id FROM instructor");
		while(rs.next())
			ids.add(rs.getString(1));

		statement.close();
		connection.close();
		
		return ids;
	}

	/**
	 * Gets a list of emails from instructor table in database
	 * @return ArrayList of emails as strings
	 * @throws SQLException on db access exception
	 * @throws ClassNotFoundException if Driver.class cannot be found in tomcat WEB-INF folder
	 */
	public ArrayList<String> getEmails() throws SQLException, ClassNotFoundException
	{
		ArrayList<String> emails = new ArrayList<String>();
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT email FROM instructor");
		while(rs.next())
			emails.add(rs.getString(1));

		statement.close();
		connection.close();
		
		return emails;
	}
	
	/**
	 * Checks whether an id already exists in the database
	 * @param id the id number to be checked
	 * @return
	 * @throws SQLException if there is a database access error
	 * @throws ClassNotFoundException if the class Driver.class cannot be found (from openConnection())
	 */
	public boolean idExists(String id) throws SQLException, ClassNotFoundException
	{
		boolean exists = false;
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT id FROM requestForm");
		while(rs.next())
			if(rs.getString(1).equals(id))
			{
				exists = true;
				break;
			}

		statement.close();
		connection.close();
		
		return exists;
	}
	
	/**
	 * Checks whether a certain Sha Sum exists in the database already
	 * @param shaSum the sha sum to be checked
	 * @return true if it is found, false otherwise
	 * @throws SQLException on database access issues
	 * @throws ClassNotFoundException if Driver.class is not found in the WEB-INF folder of tomcat
	 */
	public boolean shaSumExists(String shaSum) throws SQLException, ClassNotFoundException
	{
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT id FROM instructor WHERE sha_sum='" + shaSum + "'");
		boolean exists = rs.next();
		//close connection
		statement.close();
		connection.close();
		//return if result set has elements
		return exists;
	}
	
	/**
	 * Requires that database connection is open
	 * Creates all necessary tables for database if they are not already present
	 */
	public void createNecessaryTables() throws SQLException
	{
		Statement statement = connection.createStatement();
		String create = "CREATE TABLE IF NOT EXISTS ";
		
		//create tables if they don't already exist
		statement.executeUpdate(create + TABLES[0] + " " + INSTRUCTOR_COLUMNS);
		statement.executeUpdate(create + TABLES[1] + " " + COURSE_COLUMNS);
		statement.executeUpdate(create + TABLES[2] + " " + SCHEDULE_COLUMNS);
		statement.executeUpdate(create + TABLES[3] + " " + FORM_COLUMNS);
		statement.executeUpdate(create + TABLES[4] + " " + CHOICE_COLUMNS);
		statement.executeUpdate(create + TABLES[5] + " " + USER_COLUMNS);
	}
	
	/**
	 * opens connection to database;
	 * @throws RuntimeException
	 */
	public void openConnection() throws SQLException, ClassNotFoundException
	{
		//loads driver 
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url, user, password);
	}
}