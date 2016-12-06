<%-- 
    Document   : sign_up.jsp
    Created on : 22-Nov-2016, 12:27:48
    Author     : Conno
--%>

<%@page import="DTO.SecurityQuestion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.SecurityQuestionDao"%>
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
            <h1>Sign Up</h1>
            <hr/>

            <jsp:include page="error.jsp"/>

            <form method="post" action="Controller">
                <input type="hidden" name="action" value="create_user"/>

                <label for="first_name">Name:</label><br/>
                <input id="f_name" class="name_input" type="text" name="f_name" placeholder="First Name"  required/>
                <input id="l_name" class="name_input" type="text" name="l_name" placeholder="Last Name"  required/><br/>

                <label for="username">Username:</label><br/>
                <input id="username" class="text_input" type="text" name="username" placeholder="At Least 4 Character"  required/><br/>

                <label for="email">Email:</label><br/>
                <input id="email" class="text_input" type="email" name="email" placeholder="You@Awesome.com"  required/><br/>

                <label for="pass">Password: must contain a number, capital, lowercase <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; and special character</label><p></p><br/>
                <input id="password" class="text_input" type="password" name="pass" placeholder="Password"  required/></br>

                <label for="confirm_pass">Confirm Password:</label><br/>
                <input id ="c_pass" class="text_input" type="password" name="confirm_pass" placeholder="Confirm Password"  required/><br/>
                <br/><br/>
                <h3>Security Questions:</h3>
                <p>(Must select 3 different questions)</p><br/>
                <label for="select1">Questions 1:</label><br/>
                <select name="select1" id="select1" style="width:70%;">
                    <%
                        SecurityQuestionDao sqDao = new SecurityQuestionDao("library");
                        ArrayList<SecurityQuestion> questions = sqDao.getAllSecurityQuestions();

                        out.print(questions.size());

                        if (questions != null && questions.size() > 0) {
                            for (SecurityQuestion q : questions) {
                    %>

                    <option value="<%=q.getSq_id()%>"> <%=q.getQuestion()%> </option>

                    <%      }
                        } else {
                            session.setAttribute("error", "ERROR GETTING SECURITYQUESTIONS FROM DATABASE");
                        }
                    %>
                </select>
                <input id="sQ1" class="text_input" type="password" name="securityQ1" placeholder="Answer" required/>
                <br/><br/>
                <label for="select1">Questions 2:</label><br/>
                <select name="select2" id="select2" style="width:70%;">
                    <% for (SecurityQuestion q : questions) {%>  

                    <option value="<%=q.getSq_id()%>"> <%=q.getQuestion()%> </option>
                    <% } %>
                </select>
                <input id="sQ2" class="text_input" type="password" name="securityQ2" placeholder="Answer" required/>
                <br/><br/>
                <label for="select1">Questions 3:</label><br/>
                <select name="select3" id="select3" style="width:70%;">
                    <% for (SecurityQuestion q : questions) {%>  

                    <option value="<%=q.getSq_id()%>"> <%=q.getQuestion()%> </option>
                    <% }%>
                </select>
                <input id="sQ3" class="text_input" type="password" name="securityQ3" placeholder="Answer" required/>

                <br/><br/>
                <input id="submit" class="button" type="submit" name="submit" value="Create Account"/>
                <input class="button" type="reset" name="clear" value="Clear"/>
            </form>
        </section>
    </body>
</html>