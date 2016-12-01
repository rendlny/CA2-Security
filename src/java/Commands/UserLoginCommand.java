/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDao;
import DTO.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class UserLoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        
        if(session.getAttribute("logged_in") == null) {

            String username = request.getParameter("username");
            String password = request.getParameter("pass");

            if((username != null && !username.equals("")) &&
               (password != null && !password.equals(""))
            ) {

                UserDao userDao = new UserDao("library");
                User temp_user = userDao.login(username, password);
                if(temp_user != null) {
                    session.setAttribute("logged_in", temp_user);
                    
                    //checking if password needs to be updated
                    //int days = (new java.util.Date()).getTime() - (temp_user.getDate()).getTime();
                    //if(days>90){
                    //    session.setAttribute("notify", "You're password is out "
                    //            + "of date, please change it via your profile");
                    //}
                    forwardToJsp = "Controller?action=Home";
                } else {
                    session.setAttribute("error", "No user with matching credentials. Please try again");
                    forwardToJsp = "login.jsp";
                }
            } else {
                session.setAttribute("error", "Missing input data please try again");
                forwardToJsp = "login.jsp";
            }
        } else {
            session.setAttribute("error", "You are already logged in. Please log out first");
            forwardToJsp = "home.jsp";
        }
        
        return forwardToJsp;
    }
    
}
