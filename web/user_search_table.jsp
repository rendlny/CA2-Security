<%-- 
    Document   : user_search_table
    Created on : 05-Dec-2016, 16:51:22
    Author     : Conno
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.User"%>
<%
    if (session.getAttribute("logged_in") == null) {
        session.setAttribute("error", "You must be logged in to access that page");
        response.sendRedirect("login.jsp");
    } else {
%>
<h2>Results: </h2>
<%
    ArrayList<User> users = (ArrayList<User>) session.getAttribute("found_users");

    if (users != null && users.size() > 0) {
%>
<form method="post" action="Controller">
    <input type = "hidden" name = "action" value = "check_out"/>
    <table>
        <tr>
            <th>User Name</th>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Last Password Update</th>
            <th>&nbsp;</th>
        </tr>

        <%
            for (User u : users) {
        %>
        <tr>
            <td><%= u.getUsername()%></td>
            <td><%= u.getEmail()%></td>
            <td><%= u.getF_name()%></td>
            <td><%= u.getL_name()%></td>
            <td><%= u.getDate()%></td>
        </tr>
        <%
            }
        %>

    </table>
</form>
<%
} else {
%>
<p>There was no results found</p>
<%
        }

        session.setAttribute("found_users", null);
    }
%>
