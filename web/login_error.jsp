<%-- 
    Document   : login_error
    Created on : 23-Nov-2016, 21:37:44
    Author     : Conno
--%>
<% 
    int error_type = 0;

    try {
        error_type = Integer.parseInt(request.getParameter("error"));
    } catch (NumberFormatException ex) {
        error_type = 0;
    }

    if(error_type > 0) {
        out.print("<div class='error_container'>");
        out.print("<p class='error_msg'>");

        String error_msg = null;

        if(error_type == 1) {
            error_msg = "Input missing from form. Please try again.";
        } else if (error_type == 2) {
            error_msg = "No matching account details found. Please try again";
        } else if (error_type == 3) {
            error_msg = "An unknown error occured. Please wait a while and try again";
        }
        out.print(error_msg);
        out.print("</p>");
        out.print("</div>");
    }

%>
