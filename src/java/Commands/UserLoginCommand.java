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
                    forwardToJsp = "home.jsp";
                } else {
                    forwardToJsp = "login.jsp?error=1";
                }
            } else {
                forwardToJsp = "login.jsp?error=2";
            }
        } else {
            forwardToJsp = "home.jsp?error=1";
        }
        
        return forwardToJsp;
    }
    
}
