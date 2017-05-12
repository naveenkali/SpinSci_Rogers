<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <script>

	 function pageLoad() {
		window.history.forward();
	}
	
</script> -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="images/SpinSciLogo.png">
<title>Forget Password</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script src="js/bootstrap-select.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap-select.min.css">
<link rel="stylesheet" href="css/dashboard.css" type="text/css" />
<script type="text/javascript" src="js/pages/indexJS.js">


</script>

<style>
.labelClass {
	float: left;
	width: 80px;
}

.login {
	width: 100px;
	height: 100px;
	position: absolute;
	top: 0;
	align-items: center;
	display: flex;
	justify-content: center;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
	display: flex;
	justify-content: center;
}
</style>

</head>
<body onload="pageLoad();">
<form>



	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">

			<a target="_blank" href="http://spinsci.com/"> <img
				class="logo-main logo-reg"
				src="http://snap.spinsci.com/wp-content/uploads/2015/04/SpinSciLogo-360x100.png"
				alt="Snap" height="40" width="144">
			</a>
		</div>

	</div>
	</nav>

	<div class="login">
		<!--  BOOTSTRAP ALERTS -->
		
		
		<table>
			<tr>
				<td colspan="5" style="width: 20px">&nbsp;</td>
			</tr>
			<!-- <tr>
				<td><label class="labelClass"> Enter PassCode: </label></td>
				<td>&nbsp;</td>
				<td><input type="text" name="passcode" id="passcode"
					placeholder="Please Enter Code" /></td>
			</tr> -->
			<tr>
				<td style="width: 20px">&nbsp;</td>
			</tr>
			<tr>
				<td><label class="labelClass">New Password: </label></td>
				<td>&nbsp;</td>
				<td><input type="password" name="password" id="password"
				placeholder="Please Enter Password" /></td>
			</tr>
			<tr>
				<td style="wid  th: 20px">&nbsp;</td>
			</tr>
			<tr>
				<td><label class="labelClass">Confirm New Password: </label></td>
				<td>&nbsp;</td>
				<td><input type="password" name="cpassword" id="cpassword" 
				placeholder="Please Enter Confirm Password"/></td>
			</tr>
			<tr>
				<td style="wid  th: 20px">&nbsp;</td>
			</tr>
			<tr>
				<td> <button id="ChangePassword"  name="ChangePassword"  class="btn btn-success" 
					onclick="validatePassword();">Change Password </button> </td>
			</tr>
			
			<tr>
				<td style="width: 20px">&nbsp;</td>
			</tr>
			
		</table>
	</div>
<!-- Modal -->
		<div id="alertContainer"></div>
</form>
</body>
</html>
