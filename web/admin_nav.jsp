<%-- 
    Document   : admin_nav
    Created on : 05-Dec-2016, 11:25:31
    Author     : Conno
--%>
<%@page import="DTO.User"%>
<%
    User user = (User) session.getAttribute("logged_in");
    if (user == null) {
        session.setAttribute("error", "You must be logged in to access that page");
        response.sendRedirect("login.jsp");
    } else {
        String username = user.getUsername();
%>
<div id="bar">
    <nav>
        <ul>
            <a href='user_loans.jsp'><li>My Loans</li></a>
            <a href='search.jsp'><li>Search</li></a>            
            <a href='add_title.jsp'><li>Add Title</li></a>
            <a class='right' href='logout.jsp'><li>Logout</li></a>
            <a class='right' href='profile.jsp'><li><%=username%></li></a>
        </ul>
    </nav>
</div>
<% }%>
