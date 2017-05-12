<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="images/SpinSciLogo.png">
<title>Performer</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script src="js/bootstrap-select.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap-select.min.css">
<link rel="stylesheet" href="css/dashboard.css" type="text/css" />
<script type="text/javascript" src="js/pages/indexJS.js"></script>
<style>
.thbackground {
	display: table-header-group;
	background-color: #337ab7;
}
.myjustification { 
  color: #000; 
  font-family:arial; 
 
}
</style>
<script>

if(null==localStorage.getItem("prlogin")||""==localStorage.getItem("prlogin")){
	window.location.href="Login.jsp";
}
	
</script>

<%
System.out.println("username=="+session.getAttribute("username"));
if(null==session.getAttribute("username"))
{
%>
<script>
window.location.href="Login.jsp";
</script>
<% }%>
</head>


<body onload="return display_History();">
	<!--  BOOTSTRAP ALERTS -->
	<!-- Modal -->
	<div id="alertContainer"></div>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">

			<a target="_blank" href="http://spinsci.com/"> <img
				class="logo-main logo-reg"
				src="http://snap.spinsci.com/wp-content/uploads/2015/04/SpinSciLogo-360x100.png"
				alt="Snap" height="40" width="144">
			</a>
		</div>
		
		<div id="navbar" class="navbar-collapse collapse">
		 
			<ul class="nav navbar-nav navbar-right">
			<img
				class="logo-main logo-reg"
				src="./Photo/<%=session.getAttribute("username")%>.jpg"
				alt="Snap" height="50" width="50">
				<li><a href="#" onclick="Logout();">Log out</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active" id="Performer_Id" onclick="display_Performer();"><a
						href="#">Performer<span class="sr-only">(current)</span></a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li class="active" id="History_Id" onclick="display_History();"><a
						href="#">Release History<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>

			<div id="maindiv"
				class="col-sm-3 col-sm-offset-3 col-md-10 col-md-offset-2 main"
				style="display: none"></div>
		</div>

	</div>

</body>
</html>