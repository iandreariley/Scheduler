<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	window.onload = function() 
	{
	    document.getElementById('ifYes').style.display = 'none';
	    document.getElementById('ifNo').style.display = 'none';
	}
	
	 function yesnoCheck() 
	{
	    if (document.getElementById('yesCheck').checked) //if choose one whose id = yescheck
	    {
	        document.getElementById('ifYes').style.display = 'block';
	        document.getElementById('ifNo').style.display = 'none';
	    } 
	    else if(document.getElementById('noCheck').checked) //if choose one whose id = nocheck 
	    {
	        document.getElementById('ifNo').style.display = 'block';
	        document.getElementById('ifYes').style.display = 'none';
	   }
	}
	
</script>

</head>

<body>
	Choose your status:<br>
	Part-time<input type="radio" onclick="javascript:yesnoCheck();" name="yesno" id="yesCheck"/>
	Full-time<input type="radio" onclick="javascript:yesnoCheck();" name="yesno" id="noCheck"/>
	<br>
	
	<!-- Part Time Below -->
	
	<!--  form 1 here for part time instructors with submit button-->
	<form method="post" action="/Scheduler/ClassPreferenceServlet">
	<div align = "center" id="ifYes" style="display:none">
	<br>
	(Part-time Mode)
	<br>*Select the classes you would most like to teach followed by<br>
		    the days and time block during which you'd like to teach them.<br><br>
	
	 FirstChoice:<br>
		    Class<br>
		    <select name="1">
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
		    <select name="2">
				<option value="">*2-3 unit classes*</option>
				<option value="M">M</option>
				<option value="T">T</option>
				<option value="W">W</option>
				<option value="R">R</option>
				<option value="F">F</option>
				<option value="MW">MW</option>
				<option value="TR">TR</option>
			</select><br> <br>
			
			Starting Time<br>
		    <select name="3">
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
		    <select name="4">
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
		    <select name="5">
	              <option value="">*2-3 unit classes*</option>
	              <option value="M">M</option>
	              <option value="T">T</option>
	              <option value="W">W</option>
	              <option value="R">R</option>
	              <option value="F">F</option>
	              <option value="MW">MW</option>
	              <option value="TR">TR</option>
		    </select><br><br>
	
		    Time<br>
		     <select name="6">
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
		    <br>
		     <input type="submit" VALUE="Submit Form">
		    <a href="/Scheduler/admin.jsp">Sign in as administrator</a>
		    
	</div></form>
	
	
	<!-- Full Time Below -->
	<!--  form 2 here for full time instructors with submit button-->
	<form method="post" action="/Scheduler/ClassPreferenceServlet">
	<div  align = "center" id="ifNo" style="display:block">
	 <br>
	(Full-time Mode)
	<br>
	*Select the classes you would most like to teach followed by<br>
		    the days and time block during which you'd like to teach them.<br><br>
	
		    FirstChoice:<br>
		    Class<br>
		    <select name="1">
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
		    <select name="2">
				<option value="">*4-5 unit classes</option>
				<option value="MW">MW</option>
				<option value="TR">TR</option>
				<option value="MWF">MWF</option>
				<option value="MTRF">M</option>
				<option value="TWRF">M</option>
			</select><br> <br>
			
			Starting Time<br>
		    <select name="3">
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
		    <select name="4">
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
		    <select name="5">
	              <option value="">*4-5 unit classes</option>
	              <option value="MW">MW</option>
	              <option value="TR">TR</option>
	              <option value="MWF">MWF</option>
	              <option value="MTRF">M</option>
	              <option value="TWRF">M</option>
		    </select><br><br>
	
		    Time<br>
		    <select name = "6">
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
		    <select name="7">
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
		    <select name="8">
	              <option value="">*4-5 unit classes</option>
	              <option value="MW">MW</option>
	              <option value="TR">TR</option>
	              <option value="MWF">MWF</option>
	              <option value="MTRF">M</option>
	              <option value="TWRF">M</option>
		    </select><br><br>
	
		    Time<br>
		    <select name="9">
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
		    <br>
		     <input type="submit" VALUE="Submit Form">
		    <a href="/Scheduler/admin.jsp">Sign in as administrator</a>
	</div></form>
	


<!--  
	executeUpdate() -> database UPDATE statements 
	executeQuery() -> database QUERY statements 
	execute() -> anything that comes in  
-->

</body> 
</html>