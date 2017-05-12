<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: rgb(233,254,253);">
<center>
<table align="center">
<tr align="center"><td style="color:rgb(84,103,237)  " rowspan="4" align="center">
<h3 >Congratulations <% String[] str=session.getAttribute("perfomerUserName").toString().split("@");
str[0]=str[0].toUpperCase();
str[0]=str[0].charAt(0)+str[0].substring(1,str[0].length()).toLowerCase();
System.out.println(" congrats :"+str[0]);%>
<%=str[0] %> <br></br> The best performer of the release<br> </br> <%=session.getAttribute("releaseDate")%> <%=session.getAttribute("releaseyear")%></h3>
<td rowspan="4" width="10%">&nbsp;</td>
<td rowspan="4"><img align="middle"
				class="logo-main logo-reg"
				src="./Photo/<%=session.getAttribute("perfomerUserName")%>.jpg"
				alt="Snap" height="300" width="300" ></td></tr>
				<tr><td></td></tr>

</table>
		</center>
		</body>
</html>