<%
    session.setAttribute("logged_in", null);
    session.setAttribute("error", null);
    session.setAttribute("notify", null);
    response.sendRedirect("login.jsp");
%>