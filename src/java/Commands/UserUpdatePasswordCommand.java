/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDao;
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
public class UserUpdatePasswordCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        UserDao userDao = new UserDao("library");

        User logged_in = (User) session.getAttribute("logged_in");
        if (logged_in != null) {
            //getting logged in users original password
            String oldPass = logged_in.getPassword();
            String salt = logged_in.getSalt();

            //setting up parameters
            User tempU = new User();
            String tryPass = null;
            String newPass = null;
            String matchNewPass = null;

            //getting parameters from form
            tryPass = request.getParameter("old_pass");
            newPass = request.getParameter("new_pass");
            matchNewPass = request.getParameter("conf_pass");

            if (tryPass == null || newPass == null || matchNewPass == null
                    || tryPass.equals("") || newPass.equals("") || matchNewPass.equals("")) {
                session.setAttribute("error", "Please fill in the 3 boxes");
                forwardToJsp = "profile.jsp";
            } else if (!newPass.equals(matchNewPass)) {
                session.setAttribute("error", "New passwords do not match");
                forwardToJsp = "profile.jsp";
            } else {

                String username = logged_in.getUsername();

                //checking password
                String valid = logged_in.passwordChecker(newPass, username);
                if (!valid.equals("true")) {
                    session.setAttribute("error", valid);
                    forwardToJsp = "profile.jsp";
                } else {

                    String saltedTryPass = tempU.generateSaltedHash(tryPass, salt);

                    if (saltedTryPass.equals(oldPass)) {
                        String newSalt = null;
                        String newSaltedPass = null;

                        //Checking Salt is unique
                        boolean check = false;
                        do {
                            check = false;

                            newSalt = tempU.generateSalt();

                            if (userDao.checkSalt(newSalt)) {
                                newSaltedPass = tempU.generateSaltedHash(newPass, newSalt);
                            } else {
                                check = true;
                            }

                        } while (check);

                        DateFormat df = new SimpleDateFormat("yy/MM/dd");
                        Date dateobj = new Date();
                        String date = df.format(dateobj);

                        boolean updateCheck = userDao.updatePassword(username, oldPass, newSaltedPass, newSalt, date);
                        if (updateCheck == true) {
                            session.setAttribute("notify", "<p>Password Updated</p>");
                            forwardToJsp = "profile.jsp";
                        } else {
                            session.setAttribute("error", "Password Update Failed");
                            forwardToJsp = "profile.jsp";
                        }

                    } else {
                        session.setAttribute("error", "The Old password is incorrect");
                        forwardToJsp = "profile.jsp";
                    }
                }
            }
        } else {
            session.setAttribute("error", "You have to be logged in to use this functionality");
            forwardToJsp = "login.jsp";
        }

        return forwardToJsp;
    }

}
