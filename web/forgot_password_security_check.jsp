<%-- 
    Document   : retrieve_password
    Created on : 06-Dec-2016, 09:59:17
    Author     : Ren
--%>

<%@page import="DTO.SecurityQuestion"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp"/>        
        <link href="css/account.css" rel="stylesheet" type="text/css">
    </head>
    <jsp:include page="account_nav.jsp"/>
    <body>
        <section>
            <h1>Forgot Password</h1>
            <hr/>
            <jsp:include page="error.jsp"/>
            <jsp:include page="notify.jsp"/>
            <form method="post" action="Controller">
                <input type="hidden" name="action" value="check_security_answers"/>
                <h3>Security Questions</h3><br/>
                <%
                    String fowardToJsp = null;
                    ArrayList<SecurityQuestion> usersSQs = (ArrayList<SecurityQuestion>) session.getAttribute("userSecurityQuestions");
                    if (usersSQs.size() > 0) {
                        int i = 1;
                        for (SecurityQuestion q : usersSQs) {
                            if (q == null) {
                                session.setAttribute("error", "Question not set, contact admin");
                                fowardToJsp="forgot_password.jsp";
                            } else {
                %>          <p><%=q.getQuestion()%> <p/>
                <input class="text_input" type="password" name="sq<%=i%>" placeholder="answer" required/><br/>
                <% i++;
                            }
                        }
                    } else {
                        session.setAttribute("error", "Failed to get security questions, contact admin" + usersSQs.size());
                        fowardToJsp="forgot_password.jsp";
                                }
                if(fowardToJsp!=null){response.sendRedirect(fowardToJsp); } %>
                <input class="button" type="submit" name="submit" value="Submit"/>
                <input class="button" type="reset" name="reset" value="Clear"/>
            </form>
        </section>
    </body>
</html>
