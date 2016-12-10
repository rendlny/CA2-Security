<%-- 
    Document   : add_title
    Created on : 07-Dec-2016, 20:58:47
    Author     : Conno
--%>

<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
            User user = null;

            if(session.getAttribute("logged_in")== null) {
                session.setAttribute("error", "You must be logged in to access that page");
                response.sendRedirect("login.jsp");
            } else {
                user = (User)session.getAttribute("logged_in");
            
        %>
    <head>
        
        <jsp:include page="head.jsp"/>
        <link href="css/add_title.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%
            String nav_type = null;
            
            if (user.getRole_type_name().equals("admin")) {
                nav_type = "admin_nav.jsp";
            } else {
                nav_type = "home_nav.jsp";
            }
        %>
        <jsp:include page="<%=nav_type%>"/>
        <section>
            <h1>Add Title</h1>
            <hr/>
            <form method = "POST" action = "Controller">
                
                <input type = "hidden" name = "action" value = "add_title" />
                
                <label for = "book_title" >Book Title:</label><br/>
                <input type = "text" name = "book_title" placeholder = "Book Title" /><br/>
                <label for = "author" >Author:</label><br/>
                <input type = "text" name = "author" placeholder = "Author" /><br/>
                <label for = "publisher" >Publisher:</label><br/>
                <input type = "text" name = "publisher" placeholder = "Publisher" /><br/>
                <label for = "year" >Year Released:</label><br/>
                <input type = "number" name = "year" placeholder = "Year Released" /><br/>
                <label for = "stock" >Stock:</label><br/>
                <input type = "number" name = "stock" placeholder = "Stock" /><br/><br/>
                
                <input id="submit" class="button" type="submit" name="submit" value="Create Account"/>
                <input class="button" type="reset" name="clear" value="Clear"/>
            </form>
        </section>
    </body>
    <% } %>
</html>
