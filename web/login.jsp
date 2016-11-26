<%-- 
    Document   : login
    Created on : 21-Nov-2016, 20:38:15
    Author     : Ren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp"/>        
        <link href="css/account.css" rel="stylesheet" type="text/css">
    </head>
    <jsp:include page="account_nav.jsp"/>
    <body>
        <section>
            <h1>Login</h1>
            <hr/>

            <jsp:include page="error.jsp"/>
            
            <form method="post" action="Controller">

                <input type="hidden" name="action" value="login_user"/>

                <label for="username">Username:</label><br/>
                <input class="text_input" type="text" name="username" placeholder="Username" required/><br/>

                <label for="pass">Password:</label></br>
                <input class="text_input" type="password" name="pass" placeholder="Password" required/></br>

                <br/><br/>
                <input class="button" type="submit" name="submit" value="Login"/>
                <input class="button" type="reset" name="reset" value="Clear"/>
                <br/><br/>
            </form>
        </section>
    </body>
</html>
