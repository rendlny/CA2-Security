<%-- 
    Document   : user_loans
    Created on : 26-Nov-2016, 17:51:35
    Author     : Conno
--%>

<%@page import="DAO.LoanDao"%>
<%@page import="DAO.TitleDao"%>
<%@page import="DTO.Loan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
    if(session.getAttribute("logged_in")== null) {
        session.setAttribute("error", "You must be logged in to access that page");
        response.sendRedirect("login.jsp");
    }else{
%>
    <head>
        <jsp:include page = "head.jsp"/>
        <link href="css/home.css" rel="stylesheet" type="text/css">
        <%
            TitleDao titleDao = new TitleDao("library");
            LoanDao loanDao = new LoanDao("library");
            User user = (User) session.getAttribute("logged_in");
        %>
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
            <h1><%=user.getUsername()%>'s Loans</h1>
            <hr/>
            <jsp:include page="error.jsp"/>
            <jsp:include page="notify.jsp"/>
            <form method="post" action="Controller">
                <input type = "hidden" name = "action" value = "return_title"/>
                <table>
                    <%
                        ArrayList<Loan> loans = loanDao.listUserLoans(user.getUser_id());

                        if (loans.size() < 1) {
                            out.print("You currently have no loans");
                        } else {
                    %>

                    <tr>
                        <th>Book Title</th>
                        <th>Check Out Date</th>
                        <th>Return Date</th>
                        <th>Status</th>
                        <th>&nbsp;</th>
                    </tr>
                    <%
                        for (Loan l : loans) {
                    %>
                    <tr>
                        <td><%= titleDao.getTitleById(l.getTitle_id()).getBook_title()%></td>
                        <td><%= l.getWithdraw_date()%></td>
                        <td><%= l.getReturn_by_date()%></td>

                        <%
                            String status = l.calcStatus();
                            String class_type = null;

                            if (status.contains("Good")) {
                                class_type = "good";
                            } else if (status.contains("Moderate")) {
                                class_type = "moderate";
                            } else {
                                class_type = "late";
                            }

                        %>

                        <td class="<%=class_type%>"><%=status%></td>
                        <td><button class="check_button" name="loan_id" value="<%= l.getLoan_id()%>">Return</button></td>
                    </tr>
                    <%
                            }
                        }
                    %>

                </table>
            </form>
        </section>
    </body>
    <% } %>
</html>
