<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Class Preference Sheet</title>
</head>
<body bgcolor="WHITE">
<!-- Filter -->
<%String postURLstring="/Scheduler/ClassPreferenceServlet";
  String queryString = request.getQueryString();
  if(queryString != null)
  {
  	postURLstring = postURLstring + "?" + queryString;
  }
%>
<table border="2" cellpadding="2">
 <tr><td WIDTH="400">
  
	  <form method="post" action="<%=postURLstring%>">

	    <input type="radio" name="fulltime" value="1" checked="checked">Full-time<br>
	    <input type="radio" name="fulltime" value="0">Part-time<br><br>

	    ID# <input type="text" name="id"  size=30 value=<%= request.getParameter("id")%>><br>
	    First Name<input type="text" name="first_name" size=30 value=<%=request.getParameter("first")%>><br>
	    Last Name<input type="text" name="last_name" size=30 value=<%=request.getParameter("last")%>><br><br>

	    Select the classes you would most like to teach followed by<br>
	    the days and time block during which you'd like to teach them.<br><br>

	    FirstChoice:<br>
	    Class<br>
	    <select name="first_choice">
			<option value="3">3</option>
			<option value="5A">5A</option>
			<option value="5B">5B</option>
			<option value="5C">5C</option>
			<option value="7A">7A</option>
			<option value="7B">7B</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="15">15</option>
			<option value="22">22</option>
			<option value="55">55</option>
			<option value="125">125</option>
			<option value="128A">128A</option>
			<option value="131">131</option>
			<option value="134A">134A</option>
			<option value="134B">134B</option>
			<option value="139">139</option>
			<option value="141">141</option>
			<option value="171A">171A</option>
			<option value="330">330</option>
			<option value="331">331</option>
			<option value="400A">400A</option>
			<option value="400B">400B</option>
			<option value="402">402</option>
			<option value="450">450</option>
		</select><br><br>

	    Days <br> 
	    <select name="first_choice_days">
			<option value="">*2-3 unit classes*</option>
			<option value="M">M</option>
			<option value="T">T</option>
			<option value="W">W</option>
			<option value="R">R</option>
			<option value="F">F</option>
			<option value="MW">MW</option>
			<option value="TR">TR</option>
			<option value="">*4-5 unit classes</option>
			<option value="MW">MW</option>
			<option value="TR">TR</option>
			<option value="MWF">MWF</option>
			<option value="MTRF">M</option>
			<option value="TWRF">M</option>
		</select><br> <br>
		
		Starting Time<br>
	    <select name="first_choice_time">
	      <option value="7:00am">7:00am</option>
	      <option value="9:00am">9:00am</option>
          <option value="10:00am">10:00am</option>
          <option value="12:00pm">12:00pm</option>
          <option value="1:00pm">1:00pm</option>
          <option value="2:00pm">2:00am</option>
          <option value="3:00pm">3:00pm</option>
          <option value="4:00pm">4:00pm</option>
          <option value="6:00pm">6:00am</option>
          <option value="7:00pm">7:00am</option>
	      <option value="8:00pm">8:00pm</option>
	    </select><br><br>
	    Note: Four- and five- unit classes are 2.5 hours long<br>
	    when meeting two days per week, and 1.5 when meeting three<br>
	    days per week and 1.15 hours long when meeting four days<br>
	    per week.
	    Two- and Three- unit classes are 1.15 hours long when<br>
	    meeting two days per week, and 1.5 hours when meeting<br>
	    one day per week.<br><br>

	    Second Choice:<br>

	    Class<br>
	    <select name="second_choice">
              <option value="3">3</option>
              <option value="5A">5A</option>
              <option value="5B">5B</option>
              <option value="5C">5C</option>
              <option value="7A">7A</option>
              <option value="7B">7B</option>
              <option value="9">9</option>
              <option value="10">10</option>
              <option value="15">15</option>
              <option value="22">22</option>
              <option value="55">55</option>
              <option value="125">125</option>
              <option value="128A">128A</option>
              <option value="131">131</option>
              <option value="134A">134A</option>
              <option value="134B">134B</option>
              <option value="139">139</option>
              <option value="141">141</option>
              <option value="171A">171A</option>
              <option value="330">330</option>
              <option value="331">331</option>
              <option value="400A">400A</option>
              <option value="400B">400B</option>
              <option value="402">402</option>
              <option value="450">450</option>
	    </select><br><br>

	    Days<br>
	    <select name="second_choice_Days">
              <option value="">*2-3 unit classes*</option>
              <option value="M">M</option>
              <option value="T">T</option>
              <option value="W">W</option>
              <option value="R">R</option>
              <option value="F">F</option>
              <option value="MW">MW</option>
              <option value="TR">TR</option>
              <option value="">*4-5 unit classes</option>
              <option value="MW">MW</option>
              <option value="TR">TR</option>
              <option value="MWF">MWF</option>
              <option value="MTRF">M</option>
              <option value="TWRF">M</option>
	    </select><br><br>

	    Time<br>
	    <select>
              <option value="7:00am">7:00am</option>
              <option value="9:00am">9:00am</option>
              <option value="10:00am">10:00am</option>
              <option value="12:00pm">12:00pm</option>
              <option value="1:00pm">1:00pm</option>
              <option value="2:00pm">2:00am</option>
              <option value="3:00pm">3:00pm</option>
              <option value="4:00pm">4:00pm</option>
              <option value="6:00pm">6:00am</option>
              <option value="7:00pm">7:00am</option>
              <option value="8:00pm">8:00pm</option>
	    </select><br><br>

	    Third Choice (if teaching three classes):<br>
	    Class<br>
	    <select name="third_choice">
              <option value="3">3</option>
              <option value="5A">5A</option>
              <option value="5B">5B</option>
              <option value="5C">5C</option>
              <option value="7A">7A</option>
              <option value="7B">7B</option>
              <option value="9">9</option>
              <option value="10">10</option>
              <option value="15">15</option>
              <option value="22">22</option>
              <option value="55">55</option>
              <option value="125">125</option>
              <option value="128A">128A</option>
              <option value="131">131</option>
              <option value="134A">134A</option>
              <option value="134B">134B</option>
              <option value="139">139</option>
              <option value="141">141</option>
              <option value="171A">171A</option>
              <option value="330">330</option>
              <option value="331">331</option>
              <option value="400A">400A</option>
              <option value="400B">400B</option>
              <option value="402">402</option>
              <option value="450">450</option>
	    </select><br><br>
	    
	    Days<br>
	    <select name="third_choice_days">
              <option value="">*2-3 unit classes*</option>
              <option value="M">M</option>
              <option value="T">T</option>
              <option value="W">W</option>
              <option value="R">R</option>
              <option value="F">F</option>
              <option value="MW">MW</option>
              <option value="TR">TR</option>
              <option value="">*4-5 unit classes</option>
              <option value="MW">MW</option>
              <option value="TR">TR</option>
              <option value="MWF">MWF</option>
              <option value="MTRF">M</option>
              <option value="TWRF">M</option>
	    </select><br><br>

	    Time<br>
	    <select name="third_choice_time">
              <option value="7:00am">7:00am</option>
              <option value="9:00am">9:00am</option>
              <option value="10:00am">10:00am</option>
              <option value="12:00pm">12:00pm</option>
              <option value="1:00pm">1:00pm</option>
              <option value="2:00pm">2:00am</option>
              <option value="3:00pm">3:00pm</option>
              <option value="4:00pm">4:00pm</option>
              <option value="6:00pm">6:00am</option>
              <option value="7:00pm">7:00am</option>
              <option value="8:00pm">8:00pm</option>
	    </select>
	    <input type="submit" VALUE="Submit Form">
	    <a href="/FinalProject/login.html">Sign in as administrator</a>
	  </form>

      </td></tr>
    </table>

  </body>
</html>