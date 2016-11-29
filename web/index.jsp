<%-- 
    Document   : index
    Created on : 23-Nov-2016, 11:50:33
    Author     : Conno
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp"/>
        <link href="css/new_landing.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <header>
            <div>
                <img src="image/library_icon.png" alt="Library Logo" />
                <h1>Buddwick Public Library</h1>
                <hr/>
                <p>Find fascinating ways to waste your time</p>
                <form name="page_select">
                    <a href="login.jsp" class="button" name="loginButton">Login</a>
                    <a href="sign_up.jsp" class="button" name="signUpButton">Sign Up</a>
                </form>
            </div>
        </header>
    </body>
</html>
