<%-- 
    Document   : force_password_change
    Created on : 02-Dec-2016, 10:57:07
    Author     : Ren
--%>

<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp"/>
        <link href="css/settings.css" rel="stylesheet" type="text/css">
        <link href="css/common.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            User user = (User) session.getAttribute("logged_in");
        %>
    </head>
    <body>
        <jsp:include page="error.jsp"/>
        <jsp:include page="notify.jsp"/>
        <% if (user != null) {
                session.setAttribute("error", "You cannot access that page");
                response.sendRedirect("user_loans.jsp");
            } else {
        %>
        <form method="post" action="Controller">

            <input type = "hidden" name= "action" value = "force_update_password" />

            <label for="old_pass">Password: must contain a number, capital, lowercase <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; and special character</label><br/>
            <input class="text_input" type="password" name="old_pass" placeholder="Old password"/><br/>
            <input class="text_input" type="password" name="new_pass" placeholder="New password"/><br/>
            <input class="text_input" type="password" name="conf_pass" placeholder="Confirm password"/><br/>
            <input class="button" type="submit" name="submit" value="Update Password" />
        </form>
        <% }%>
    </body>
</html>
