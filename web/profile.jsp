<%-- 
    Document   : profile
    Created on : 26-Nov-2016, 20:52:49
    Author     : Conno
--%>

<%@page import="DTO.SecurityQuestion"%>
<%@page import="DAO.SecurityQuestionDao"%>
<%@page import="DAO.UserQuestionDao"%>
<%@page import="DTO.UserQuestion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="logged_in_check.jsp"/>
        <jsp:include page="head.jsp"/>
        <link href="css/settings.css" rel="stylesheet" type="text/css">
        <%
            User user = (User) session.getAttribute("logged_in");
        %>
    </head>
    <body>
        <%
            String nav_type = null;

            if (user.isIs_admin()) {
                nav_type = "admin_nav.jsp";
            } else {
                nav_type = "home_nav.jsp";
            }
        %>
        <jsp:include page="<%=nav_type%>"/>
        <section>
            <h1>Settings</h1>
            <jsp:include page="error.jsp"/>
            <jsp:include page="notify.jsp"/>
            <hr/>
            <form method="post" action="Controller">

                <input type = "hidden" name="action" value = "update_email" />

                <label for="email">Email:</label><br/>
                <input class="text_input" type="email" name="email" placeholder="You@Awesome.com" value="<%= user.getEmail()%>" required/>

                <input class="button" type="submit" name="submit" value="Update Email" />
            </form>
            <br/>
            <form method="post" action="Controller">
                <input type = "hidden" name= "action" value = "update_password" />

                <label for="old_pass">Password: must contain a number, capital, lowercase <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; and special character</label><br/>
                <input class="text_input" type="password" name="old_pass" placeholder="Old password" required/><br/>
                <input class="text_input" type="password" name="new_pass" placeholder="New password" required/><br/>
                <input class="text_input" type="password" name="conf_pass" placeholder="Confirm password" required/><br/>
                <input class="button" type="submit" name="submit" value="Update Password" />
            </form>
            <br/>
            <label>Security Questions:</label>
            <%
                UserQuestionDao userQDao = new UserQuestionDao("library");
                ArrayList<UserQuestion> userQs = userQDao.getUserQuestionByUserId(user.getUser_id());
                SecurityQuestionDao securityQDao = new SecurityQuestionDao("library");

                for (UserQuestion q : userQs) {
                    SecurityQuestion securityQ = securityQDao.getUsersSecurityQuestion(q.getSq_id());
            %>
            
            <form method="post" action="Controller">
                <input type = "hidden" name= "action" value = "update_user_question" />
                <p> <%=securityQ.getQuestion() %></p>
                <input type = "hidden" name= "q_id" value = "<%=securityQ.getSq_id() %>" />
                <input class="text_input" type="password" name="sq" placeholder="New Answer" required/><br/>
                <input class="button" type="submit" name="submit" value="Update Answers" />
            </form><br/>

            <%
                }
            %>
        </section>
    </body>
</html>
