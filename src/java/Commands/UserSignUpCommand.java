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
        
        if(session.getAttribute("logged_in") == null) {

            String f_name = request.getParameter("f_name");
            String l_name = request.getParameter("l_name");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("pass");

            if((f_name != null && !f_name.equals("")) &&
               (l_name != null && !l_name.equals("")) &&
               (username != null && !username.equals("")) &&
               (email != null && !email.equals("")) &&
               (password != null && !password.equals(""))
            ) {
                UserDao userDao = new UserDao("library");

                if(userDao.checkUsername(username) && userDao.checkEmail(email)) {
                    User newUser = new User();
                    newUser.setF_name(f_name);
                    newUser.setL_name(l_name);
                    newUser.setUsername(username);
                    newUser.setEmail(email);

                    boolean check = false;

                    do {
                        check = false;

                        String salt = User.generateSalt();

                        if(userDao.checkSalt(salt)) {
                            newUser.setSalt(salt);
                            newUser.setPassword(User.generateSaltedHash(password, salt));
                        } else {
                            check = true;
                        }

                    } while(check);

                    newUser.setDate(User.getCurrentDate());

                    if(userDao.addUser(newUser)) {
                        forwardToJsp = "login.jsp";
                    } else {
                        forwardToJsp = "sign_up.jsp?error=3";
                    }
                } else {
                    forwardToJsp = "sign_up.jsp?error=2";
                }

            } else {
                forwardToJsp = "sign_up.jsp?error=1";
            }
        } else {
            forwardToJsp = "home.jsp?error=1";
        }
        
        return forwardToJsp;
    }
    
}
