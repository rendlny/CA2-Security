<%
    if(session.getAttribute("logged_in")== null) {
        session.setAttribute("error", "You must be logged in to access that page");
        response.sendRedirect("login.jsp");
    }
%>