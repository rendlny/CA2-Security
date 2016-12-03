<%-- 
    Document   : home
    Created on : 25-Nov-2016, 19:14:17
    Author     : Conno
--%>

<%@page import="DTO.Title"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <jsp:include page="logged_in_check.jsp"/>
        
        <jsp:include page="head.jsp"/>
        <link href="css/home.css" rel="stylesheet" type="text/css">
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
            <h1>Welcome <%=user.getUsername() %></h1>
            <hr/>
            <jsp:include page="error.jsp"/>
            <form method="post" action="Controller">
                <input type = "hidden" name = "action" value = "check_out"/>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Publisher</th>
                        <th>Year Published</th>
                        <th>No. in Stock</th>
                        <th>&nbsp;</th>
                    </tr>
                    <% 
                        ArrayList<Title> titles = (ArrayList)session.getAttribute("all_titles");
                        for(Title t : titles) {
                    %>
                    <tr>
                        <td><%=t.getTitle_id()%></td>
                        <td><%=t.getBook_title()%></td>
                        <td><%=t.getAuthor()%></td>
                        <td><%=t.getPublisher()%></td>
                        <td><%=t.getYear()%></td>
                        <td><%=t.getStock()%></td>
                        <td>
                            <button class="check_button" name="title_id" value="<%=t.getTitle_id()%>">
                                Check Out
                            </button>
                        </td>
                    </tr>
                    <% 
                        }
                    %>
                </table>
            </form>
        </section>
    </body>
</html>
