<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration Page</title>
<jsp:useBean id="myAdmin" scope="session"
	class="TelCom.Model.AdminModelBean" />
	<link rel="icon" type="image/png" href="../img/command.png" />
<link rel="stylesheet"
	href="../lib/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css">
</HEAD>
<BODY>
	<div class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="">Administration Page</a>
			</div>
			<ul class="nav navbar-nav">
					<li><a class="navbar-left" href="cmd.html">Commands Page</a></li>
				</ul>
		</div>
	</div>

	<%
		// Admin connectï¿½
		if (session.getAttribute("myAdmin").toString() != "") {
	%>

	<div class="container">
		<div class="jumbotron">
			<h1>
				Welcome
				<jsp:getProperty name="myAdmin" property="login" />
			</h1>
			
			<a id="commande"><img src="../img/robot.png"/></a>
			
						<br> <br> <br>
			
			<button id="btn-start" type="button" class="btn btn-success btn-lg">
				<br> <span class="glyphicon glyphicon-play"> </span> <br>
				<br>
				<div>Start command launcher</div>
			</button>

			<button id="btn-stop" type="button" class="btn btn-warning btn-lg">
				<br> <span class="glyphicon glyphicon-stop"> </span> <br>
				<br>
				<div>Stop command launcher</div>
			</button>
			
			<br> <br> <br>
			<form action="../logout" method="post">
				<input class="btn btn-danger" type="submit" value="Déconnexion" />
			</form>
		</div>
	</div>

	<%
		}
		// Aucun admin connectï¿½
		else {
	%>
	<div class="container">
		<div class="jumbotron">
			<h1>Connexion</h1>
			<form name="login" action="../login" method="post">
				<div class="form-group">
					<label class="sr-only" for="exampleInputAmount"></label>
					<div class="input-group">
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-user"></span>
						</div>
						<input type="text" class="form-control" id="loginId" name="login"
							placeholder="login">
					</div>
				</div>
				<div class="form-group">
					<label class="sr-only" for="exampleInputAmount"></label>
					<div class="input-group">
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-pencil"></span>
						</div>
						<input type="password" class="form-control" id="passwordId"
							name="password" placeholder="password">
					</div>
				</div>

				<input class="btn btn-default" type="reset" value="Cancel" /> <input
					class="btn btn-primary" type="submit" value="Ok" />
			</form>
		</div>
	</div>
	<%
		}
	%>
	
	<script src="../lib/jquery-1.11.1.min.js"></script>
	<script src="../js/ajaxAdmin.js"></script>
	<script src="../js/jquery.cookie.js"></script>

</BODY>

</HTML>
