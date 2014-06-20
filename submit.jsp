<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
Your data has been submitted.
<br>
Your data is: <br>


<%  
	int k = 10;

		for (int i=1; i < k; i++)
		{
			String itoA = Integer.toString(i);
			String[] v = new String[k];
			v[i]=request.getParameter(itoA);
			if(v[i]!=null)
			{
				out.println(v[i]);
			}
		}

%>  

        <form action="test.jsp">
		 <input type="submit" value="BACK"/>
		</form>
        
</body>
</html>
