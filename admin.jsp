<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html> 
   <head>
    <title>Administrator</title>
  </head>
  <body>
    <!--CSV downlaod-->

    <H4>Add an Instructor</H4><br>
    <table BORDER="2" CELLPADDING="2">
      <tr><td WIDTH="400">

        <form name="addInstructor" method="post" action="/Scheduler/AddInstructorServlet">
          First Name: <input type="text" name="first_name"/><br>
          Last Name: <input type="text" name="last_name"/><br>
          ID: <input type="text" name="id"/><br>
          EMAIL: <input type="text" name="email"/><br>
          <input type="radio" name="full_time" value="1" checked="checked"/>Full-time<br>
          <input type="radio" name="full_time" value="0"/>Part-time<br>
          <input type="submit" value="submit"/>
        </form>
      </td></tr>
    </table>
    <!--Email Errrrbody-->
    <form name="email_blast" method="post" action="/Scheduler/ProfMailServlet">
      <input type="submit" value="Send Emails">
    </form><br>
    <form name="admin" method="post" action="/Scheduler/DownloadServlet">
      Click here to download the CSV
      <input type="submit" value="Download">
    </form>
  </body>

</html>
