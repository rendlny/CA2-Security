<%-- 
    Document   : title_search_table.jsp
    Created on : 03-Dec-2016, 15:09:44
    Author     : Conno
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DTO.Title"%>
<%@page import="java.util.ArrayList"%>
<h2>Results: </h2>
<%
    
    ArrayList<Title> titles = (ArrayList<Title>)session.getAttribute("found_titles");
    
    if(titles != null) {
%>
    <form method="post" action="Controller">
        <input type = "hidden" name = "action" value = "check_out"/>
        <table>
            <tr>
                <th>Title Name</th>
                <th>Author</th>
                <th>Publisher</th>
                <th>Year of Publish</th>
                <th>&nbsp;</th>
            </tr>
            <%
                for(Title t : titles) {
            %>
            <tr>
                <td><%= t.getBook_title() %></td>
                <td><%= t.getAuthor()%></td>
                <td><%= t.getPublisher()%></td>
                <td><%= t.getYear()%></td>
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
<% 
    }
    
    else {
%>
    <p>There was no results found</p>
<% 
    }

    session.setAttribute("found_titles", null);
%>
