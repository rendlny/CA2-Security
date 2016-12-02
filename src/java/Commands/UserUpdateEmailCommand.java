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
 * @author Ren
 */
public class UserUpdateEmailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao("library");

        User logged_in = (User) session.getAttribute("logged_in");
        if (logged_in == null) {
            //if user is not logged in
            session.setAttribute("error", "You have to be logged in to use this"
                    + " functionality");
            forwardToJsp = "login.jsp";
        } else {
            String newEmail = request.getParameter("email");
            //if no email was set
            if (newEmail == null || newEmail.equals("")) {
                session.setAttribute("error", "Please set new email");
                forwardToJsp = "profile.jsp";
            } else {
                //checking if email is valid
                boolean emailValid = logged_in.validateEmail(newEmail);
                if (emailValid == false) {
                    session.setAttribute("error", "Invalid email");
                    forwardToJsp = "profile.jsp";
                } else {
                    //checking email is not already in use by another user
                    boolean emailUnique = userDao.checkEmail(newEmail);
                    if (emailUnique == false) {
                        session.setAttribute("error", "That email is already in"
                                + " use by another user");
                        forwardToJsp = "profile.jsp";
                    } else {
                        String username = logged_in.getUsername();
                        //email is unique so update it
                        boolean emailUpdated = userDao.updateEmail(username, newEmail);
                        if (emailUpdated == false) {
                            session.setAttribute("error", "Failed to update "
                                    + "email");
                            forwardToJsp = "profile.jsp";
                        } else {
                            session.setAttribute("notify", "Email updated");
                            logged_in.setEmail(newEmail);
                            session.setAttribute("logged_in", logged_in);
                            forwardToJsp = "profile.jsp";
                        }
                    }
                }
            }
        }

        return forwardToJsp;
    }
}
