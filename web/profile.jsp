<%-- 
    Document   : profile
    Created on : 26-Nov-2016, 20:52:49
    Author     : Conno
--%>

<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp"/>
        <link href="css/settings.css" rel="stylesheet" type="text/css">
        <% 
            User user = null;
            
            if(session.getAttribute("logged_in") == null) {
                session.setAttribute("error", "You have been logged out, Please log in to contune");
                response.sendRedirect("login");
            } else {
                user = (User)session.getAttribute("logged_in");
            }
        %>
    </head>
    <body>
        <% 
            String nav_type = null;
            
            if(user.isIs_admin()) {
                nav_type = "admin_nav.jsp";
            } else {
                nav_type = "home_nav.jsp";
            }
        %>
        <jsp:include page="<%=nav_type%>"/>
        <section>
            <h1>Settings</h1>
            <hr/>
            <form method="post" action="Controller">
                
                <input type = "hidden" value = "update_email" />
                
                <label for="email">Email:</label><br/>
                <input class="text_input" type="email" name="email" placeholder="You@Awesome.com" value="<%= user.getEmail() %>" required/>
                
                <input class="button" type="submit" name="submit" value="Update Email" />
            </form>
            
            <form method="post" action="Controller">
                
                <input type = "hidden" value = "update_password" />
                
                <label for="old_pass">Password:</label><br/>
                <input class="text_input" type="password" name="old_pass" placeholder="Old password"/><br/>
                <input class="text_input" type="password" name="new_pass" placeholder="New password"/><br/>
                <input class="text_input" type="password" name="conf_pass" placeholder="Confirm password"/><br/>
                <input class="button" type="submit" name="submit" value="Update Password" />
            </form>
        </section>
    </body>
</html>
