<%-- 
    Document   : sign_up.jsp
    Created on : 22-Nov-2016, 12:27:48
    Author     : Conno
--%>

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
            
            <jsp:include page="signup_error.jsp"/>
            
            <form method="post" action="Controller">
                <input type="hidden" name="action" value="create_user"/>
				
                <label for="first_name">Name:</label><br/>
                <input id="f_name" class="name_input" type="text" name="f_name" placeholder="First Name"  required/>
                <input id="l_name" class="name_input" type="text" name="l_name" placeholder="Last Name"  required/><br/>

                <label for="username">Username:</label><br/>
                <input id="username" class="text_input" type="text" name="username" placeholder="At Least 4 Character"  required/><br/>

                <label for="email">Email:</label><br/>
                <input id="email" class="text_input" type="email" name="email" placeholder="You@Awesome.com"  required/><br/>

                <label for="pass">Password:</label><br/>
                <input id="password" class="text_input" type="password" name="pass" placeholder="Password"  required/></br>

                <label for="confirm_pass">Confirm Password:</label><br/>
                <input id ="c_pass" class="text_input" type="password" name="confirm_pass" placeholder="Confirm Password"  required/><br/>

                <br/><br/>
                <input id="submit" class="button" type="submit" name="submit" value="Create Account"/>
                <input class="button" type="reset" name="clear" value="Clear"/>
            </form>
        </section>
    </body>
</html>