/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDao;
import DTO.User;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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

        if (session.getAttribute("logged_in") == null) {

            String username = request.getParameter("username");
            String password = request.getParameter("pass");

            if ((username != null && !username.equals(""))
                    && (password != null && !password.equals(""))) {

                UserDao userDao = new UserDao("library");
                User temp_user = userDao.login(username, password);
                if (temp_user != null) {
                    session.setAttribute("logged_in", temp_user);

                    //checking if password needs to be updated
                    String lastChangedDate = temp_user.getDate();
                    String[] parts = lastChangedDate.split("-");
                    int year = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    int day = Integer.parseInt(parts[2]);

                    Calendar oldDate = Calendar.getInstance();
                    oldDate.set(Calendar.YEAR, year);
                    oldDate.set(Calendar.MONTH, month);
                    oldDate.set(Calendar.DAY_OF_MONTH, day);
                    Date theOldDate = oldDate.getTime();

                    Calendar currentDate = Calendar.getInstance();
                    Date theCurrDate = new Date();

                    long diff = theCurrDate.getTime() - theOldDate.getTime();
                    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                    if (days > 90) {
                        session.setAttribute("username", temp_user.getUsername());
                        session.setAttribute("logged_in", null);
                        session.setAttribute("temp_user", temp_user);
                        session.setAttribute("notify", "You're password is out "
                                + "of date, you must change it");
                        forwardToJsp = "force_password_change.jsp";
                    } else {
                        forwardToJsp = "Controller?action=Home";
                    }
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
