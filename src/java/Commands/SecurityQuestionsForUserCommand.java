/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.SecurityQuestionDao;
import DAO.UserDao;
import DAO.UserQuestionDao;
import DTO.SecurityQuestion;
import DTO.User;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class SecurityQuestionsForUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        if (session.getAttribute("logged_in") == null) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");

            if (!username.equals("") && !email.equals("") && username != null && email != null) {

                User tempUser = new User();
                tempUser.setUsername(username);
                tempUser.setEmail(email);

                //need to get id from email of username or both
                boolean emailValid = tempUser.validateEmail(email);
                if (emailValid == true) {
                    UserDao userDao = new UserDao("library");
                    //getting user id
                    int userId = userDao.getUserId(username, email);
                    if (userId > 0) {
                        session.setAttribute("user_id", userId);
                        session.setAttribute("user_name", username);
                        UserQuestionDao userQuestionDao = new UserQuestionDao("library");
                        //getting the ids of the questions the user used
                        int[] ids = userQuestionDao.getUserQuestionsIds(userId);
                        if (ids.length > 0) {
                            SecurityQuestionDao sQDao = new SecurityQuestionDao("library");
                            ArrayList<SecurityQuestion> usersSQs = null;
                            usersSQs = new ArrayList<>();
                            //getting the questions from the ids
                            SecurityQuestion userSQ = null;
                            int id = 0;
                            for (int i = 0; i < ids.length; i++) {
                                id = ids[i];
                                if (ids[i] == 0) {
                                    session.setAttribute("error", "Failed to get security questions, contact admin");
                                    forwardToJsp = "forgot_password.jsp";
                                    break;
                                } else {
                                    userSQ = sQDao.getUsersSecurityQuestion(id);
                                    usersSQs.add(userSQ);
                                }
                            }

                            if (usersSQs != null && usersSQs.size() > 0) {
                                session.setAttribute("userSecurityQuestions", usersSQs);
                                forwardToJsp = "forgot_password_security_check.jsp";
                            } else {
                                session.setAttribute("error", "Failed to get security questions, contact admin");
                                forwardToJsp = "forgot_password.jsp";
                            }
                        } else {
                            session.setAttribute("error", "Failed to get question ids, contact admin");
                            forwardToJsp = "forgot_password.jsp";
                        }
                    } else {
                        session.setAttribute("error", "Username or Email is incorrect");
                        forwardToJsp = "forgot_password.jsp";
                    }
                } else {
                    session.setAttribute("error", "Invalid email");
                    forwardToJsp = "forgot_password.jsp";
                }
            } else {
                session.setAttribute("error", "Fill in both input boxes");
                forwardToJsp = "forgot_password.jsp";
            }
        } else {
            session.setAttribute("error", "You are already logged in. Please log out first");
            forwardToJsp = "user_loans.jsp";
        }

        return forwardToJsp;
    }

}
