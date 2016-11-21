<%-- 
    Document   : login
    Created on : 21-Nov-2016, 20:38:15
    Author     : Ren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="index.jsp">Back to index</a>
        <h1>Login</h1>
        <form action="Controller" method="POST">
            <label for="username">Username: </label>
            <input name="username" size="25" type="text"/><br /><br />
            <label for="password">Password: </label>
            <input name="password" size="25" type="password"/><br /><br />
            
            <input type="submit" value="Login" />
            <input type="hidden" name ="action" value="login" />
        </form>
    </body>
</html>
