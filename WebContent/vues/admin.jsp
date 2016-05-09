<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
    <HEAD>
    	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Administration Page</title>
        <jsp:useBean id="myAdmin" scope="session" class="TelCom.Model.AdminModelBean" />
    </HEAD>
 
    <BODY>
        <H1>Administration Page</H1>
        <%
        // Admin connecté
        if(session.getAttribute("myAdmin").toString()!="")
        {
        %>
            <h3> Welcome <jsp:getProperty name="myAdmin" property="login"/>  </h3>
            <form action="../logout" method="post">
            <input type="submit" value="Déconnexion" />
         	</form>
        <%
        }
    	// Aucun admin connecté
        else 
        {
		%>
			<h3> Connexion </h3>
			<form name="login" action="../login" method="post">
				<h5> Login <input type="text"  id="loginId" name="login"> </h5>
				<h5> Password <input type="text"  id="passwordId" name="password"> </h5>
				<input type="reset" value="Cancel" />
				<input type="submit" value="Ok" />
			</form>
        <%
		}
        %>
    </BODY>
    
</HTML> 