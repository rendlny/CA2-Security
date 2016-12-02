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
public class UserSignUpCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp = null;

        if (session.getAttribute("logged_in") == null) {

            String f_name = request.getParameter("f_name");
            String l_name = request.getParameter("l_name");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("pass");
            String conf_password = request.getParameter("confirm_pass");

            if ((f_name != null && !f_name.equals(""))
                    && (l_name != null && !l_name.equals(""))
                    && (username != null && !username.equals(""))
                    && (email != null && !email.equals(""))
                    && (password != null && !password.equals(""))
                    && (conf_password != null && !conf_password.equals(""))) {
                UserDao userDao = new UserDao("library");

                if (userDao.checkUsername(username) && userDao.checkEmail(email)) {
                    User newUser = new User();
                    newUser.setF_name(f_name);
                    newUser.setL_name(l_name);
                    newUser.setUsername(username);
                    newUser.setEmail(email);

                    //checking passwords
                    String valid = newUser.passwordChecker(password, username);
                    if (!password.equals(conf_password)) {
                        session.setAttribute("error", "Passwords do not match");
                        forwardToJsp = "sign_up.jsp";
                    } else {
                        if (!valid.equals("true")) {
                            session.setAttribute("error", valid);
                            forwardToJsp = "sign_up.jsp";
                        } else {

                            boolean check = false;

                            do {
                                check = false;

                                String salt = User.generateSalt();

                                if (userDao.checkSalt(salt)) {
                                    newUser.setSalt(salt);
                                    newUser.setPassword(User.generateSaltedHash(password, salt));
                                } else {
                                    check = true;
                                }

                            } while (check);

                            newUser.setDate(User.getCurrentDate());

                            if (userDao.addUser(newUser)) {
                                forwardToJsp = "login.jsp";
                            } else {
                                session.setAttribute("error", "An unkown error occured. Please wait while we try to fix this");
                                forwardToJsp = "sign_up.jsp";
                            }
                        }
                    }
                } else {
                    session.setAttribute("error", "Username or email already in use. Please choose another");
                    forwardToJsp = "sign_up.jsp";
                }

            } else {
                session.setAttribute("error", "Missing input data. Please try again");
                forwardToJsp = "sign_up.jsp";
            }
        } else {
            session.setAttribute("error", "You are already logged in. Please log out first");
            forwardToJsp = "home.jsp";
        }

        return forwardToJsp;
    }

}
