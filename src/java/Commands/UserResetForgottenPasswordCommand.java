/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDao;
import DAO.UserQuestionDao;
import DTO.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class UserResetForgottenPasswordCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;

        if (session.getAttribute("logged_in") == null) {
            String newPass = request.getParameter("newPassword");
            String confNewPass = request.getParameter("confNewPassword");

            if (!newPass.equals("") && newPass != null && !confNewPass.equals("") && confNewPass != null) {
                if (newPass.equals(confNewPass)) {
                    User tempUser = new User();
                    int user_id = -1;
                    try {
                        user_id = Integer.parseInt(session.getAttribute("user_id") + "");
                    } catch (NumberFormatException ex) {
                        session.setAttribute("error", "Failed to parse user id, contact admin");
                        forwardToJsp = "reset_forgotten_password.jsp";
                    }
                    if (user_id > 0) {
                        UserDao userDao = new UserDao("library");
                        User user = userDao.getUserById(user_id);
                        if (user != null) {
                            String validility = tempUser.passwordChecker(newPass, user.getUsername());
                            if (validility == "true") {

                                boolean check = false;
                                String salt = null;
                                String saltedPass = null;
                                do {
                                    check = false;

                                    salt = User.generateSalt();

                                    if (userDao.checkSalt(salt)) {
                                        tempUser.setSalt(salt);
                                        saltedPass = User.generateSaltedHash(newPass, salt);
                                    } else {
                                        check = true;
                                    }

                                } while (check);

                                DateFormat df = new SimpleDateFormat("yy/MM/dd");
                                Date dateobj = new Date();
                                String date = df.format(dateobj);
                                boolean reseted = userDao.resetPassword(user_id, saltedPass, date, salt);

                                if (reseted == true) {
                                    session.setAttribute("notify", "Password successfully changed");
                                    forwardToJsp = "login.jsp";
                                } else {
                                    session.setAttribute("error", "Failed to reset password, contact admin");
                                    forwardToJsp = "reset_forgotten_password.jsp";
                                }
                            } else {
                                session.setAttribute("error", validility);
                                forwardToJsp = "reset_forgotten_password.jsp";
                            }
                        } else {
                            session.setAttribute("error", "user not set, contact admin");
                            forwardToJsp = "reset_forgotten_password.jsp";
                        }
                    } else {
                        session.setAttribute("error", "user id not set");
                        forwardToJsp = "reset_forgotten_password.jsp";

                    }
                } else {
                    session.setAttribute("error", "Passwords do not match");
                    forwardToJsp = "reset_forgotten_password.jsp";
                }
            } else {
                session.setAttribute("error", "Please fill in both input boxes");
                forwardToJsp = "reset_forgotten_password.jsp";
            }
        } else {
            session.setAttribute("error", "You are already logged in. Please log out first");
            forwardToJsp = "home.jsp";
        }

        return forwardToJsp;
    }

}
