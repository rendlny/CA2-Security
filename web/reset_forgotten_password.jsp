<%-- 
    Document   : reset_forgotten_password
    Created on : 06-Dec-2016, 18:00:13
    Author     : Ren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp"/>        
        <link href="css/account.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <section>
            <h1>Reset Password</h1>
            <hr/>
            
            <jsp:include page="error.jsp"/>
            <jsp:include page="notify.jsp"/>
            <form method="post" action="Controller">
                <input type="hidden" name="action" value="reset_forgotten_password"/>
                
                <label for="newPassword">New Password:</label><br/>
                <input class="text_input" type="password" name="newPassword" required/><br/>
                
                <label for="confNewPassword">Confirm New Password:</label><br/>
                <input class="text_input" type="password" name="confNewPassword" required/><br/>
                <input class="button" type="submit" name="submit" value="Reset Password"/>
                <input class="button" type="reset" name="reset" value="Clear"/>
                <br/><br/>
            </form>
        </section>
    </body>
</html>
