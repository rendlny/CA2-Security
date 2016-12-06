<%-- 
    Document   : forgot_password
    Created on : 06-Dec-2016, 09:46:03
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
            <h1>Forgot Password</h1>
            <hr/>
            <jsp:include page="error.jsp"/>
            <jsp:include page="notify.jsp"/>

            <form method="post" action="Controller">
                <input type="hidden" name="action" value="get_users_sq"/>

                <label for="username">Username</label><br/>
                <input class="text_input" type="text" name="username" placeholder="Username" required/><br/><br/>

                <label for="username">Email</label><br/>
                <input class="email" type="email" name="email" placeholder="You@Awesome.com" required/><br/><br/>

                <br/><br/>
                <input class="button" type="submit" name="submit" value="Submit"/>
                <input class="button" type="reset" name="reset" value="Clear"/>


            </form>
        </section>
    </body>
</html>
