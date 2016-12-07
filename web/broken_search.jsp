<%-- 
    Document   : broken_search
    Created on : 06-Dec-2016, 13:47:36
    Author     : Conno
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
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
        <jsp:include page="home_nav.jsp"/>
        <section>
            <form method = "post" action = "broken_search.jsp">
                <input type = "text" name = "search" placeholder = "Search..."/>
                <select name="action" required>
                    <option value="title_search">Titles</option>
                </select>
            </form>
            <hr/>
            
            <%
                String search_critera = request.getParameter("search");
                
                if(search_critera != null && !search_critera.equals("")) {
                    
                    String driver = "com.mysql.jdbc.Driver";
                    String url = "jdbc:mysql://localhost:3306/library";
                    String username = "root";
                    String password = "";
                    
                    Class.forName(driver);
                    
                    Connection con = DriverManager.getConnection(url, username, password);
                    
                    String query = "SELECT * FROM title WHERE book_title = '" 
                            + search_critera + "'";
                    
                    Statement s = con.createStatement();
                    ResultSet rs = s.executeQuery(query);
                    
                    %>
                    <table>
                        <tr>
                            <th>id</th>
                            <th>Title Name</th>
                            <th>Author</th>
                            <th>Publisher</th>
                            <th>Year of Publish</th>
                            <th>Stock</th>
                        </tr>
                    
                    <%
                    while(rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString(1) %></td>
                        <td><%= rs.getString(2) %></td>
                        <td><%= rs.getString(3) %></td>
                        <td><%= rs.getString(4) %></td>
                        <td><%= rs.getString(5) %></td>
                        <td><%= rs.getString(6) %></td>
                    </tr>
                    <%    
                    }
                    
                } 

            %>
                    </table>
        </section>
    </body>
</html>
