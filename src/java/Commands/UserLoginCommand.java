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
public class UserLoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao("classicmodels");
        User userLoggedIn = userDao.login(username, password);
        if (userLoggedIn == null) {
            forwardToJsp = "loginFailed.jsp";
        } else {
            forwardToJsp = "loginSuccessful.jsp";
            HttpSession session = request.getSession();
            session.setAttribute("user", userLoggedIn);

        }
        return forwardToJsp;
    }
}
