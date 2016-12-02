<%-- 
    Document   : login_error
    Created on : 23-Nov-2016, 21:37:44
    Author     : Conno
--%>
<%
    String error = (String)session.getAttribute("error");
    
    if(error != null) {
        out.print("<div class='error_container'>");
        out.print("<p class='error_msg'>");
        out.print(error);
        out.print("</p>");
        out.print("</div>");
        
        session.setAttribute("error", null);
    }
%>
