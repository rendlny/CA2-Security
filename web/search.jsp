<%-- 
    Document   : search
    Created on : 02-Dec-2016, 18:31:55
    Author     : Conno
--%>

<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="logged_in_check.jsp"/>
        
        <jsp:include page="head.jsp"/>
        <link href="css/home.css" rel="stylesheet" type="text/css">
        <link href="css/search.css" rel="stylesheet" type="text/css">
        <% 
            User user = (User)session.getAttribute("logged_in");
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
            <form method = "post" action = "Controller">
                <input type = "text" name = "search" placeholder = "Search..."/>
                <select name="action" required>
                    <option value="title_search">Titles</option>
                </select>
            </form>
            <hr/>
            <jsp:include page="error.jsp"/>
            
            <%
                if(session.getAttribute("found_titles") != null) {
            %>
                <jsp:include page="title_search_table.jsp" />
            <%
                }
            %>
            
        </section>
    </body>
</html>