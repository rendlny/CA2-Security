<%-- 
    Document   : notify
    Created on : 01-Dec-2016, 17:58:06
    Author     : Ren
--%>
<%
    String notify = (String)session.getAttribute("notify");
    
    if(notify != null) {
        out.print("<div class='notify_container'>");
        out.print("<p class='notify_msg'>");
        out.print(notify);
        out.print("</p>");
        out.print("</div>");
        
        session.setAttribute("notify", null);
    }
%>
