<%-- 
    Document   : home_nav
    Created on : 26-Nov-2016, 11:14:37
    Author     : Conno
--%>
<%@page import="DTO.User"%>
<%
    User user = (User)session.getAttribute("logged_in");
    String username = user.getUsername();
%>
<div id="bar">
    <nav>
        <ul>
            <a href='home.jsp'><li>Home</li></a>
            <a href='Controller?action=user_loans'><li>My Loans</li></a>
            <a href='Controller?action=search'><li>Search</li></a>
            <a class='right' href='logout.jsp'><li>Logout</li></a>
            <a class='right' href='profile.jsp'><li><%=username%></li></a>
        </ul>
    </nav>
</div>
