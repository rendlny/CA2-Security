/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserQuestionDao;
import DTO.UserQuestion;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class SecurityQuestionAnswersCheck implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        if (session.getAttribute("logged_in") == null) {
            String answer1 = request.getParameter("sq1");
            String answer2 = request.getParameter("sq2");
            String answer3 = request.getParameter("sq3");
            String[] answerTry = new String[]{answer1, answer2, answer3};

            if (!answer1.equals("") && !answer2.equals("") && !answer3.equals("")) {
                int correctAnswers = 0;
                UserQuestionDao userQDao = new UserQuestionDao("library");
                int user_id = Integer.parseInt("" + session.getAttribute("user_id"));
                if (user_id > 0) {
                    session.setAttribute("user_id", user_id);
                    ArrayList<UserQuestion> userQuestions = userQDao.getUserQuestionByUserId(user_id);
                    
                    String saltedTry = null;
                    int i  = 0;
                    for(UserQuestion q : userQuestions){
                        saltedTry = UserQuestion.generateSaltedHash(answerTry[i], q.getSalt());
                        if (saltedTry.equals(q.getAnswer())) {
                            correctAnswers++;
                        }
                        i++;
                    }
                    if (correctAnswers >= 2) {
                        forwardToJsp = "reset_forgotten_password.jsp";
                    } else {
                        session.setAttribute("error", "You must get at least 2 answers correct to reset your password");
                        forwardToJsp = "forgot_password_security_check.jsp";
                    }

                } else {
                    session.setAttribute("error", "Failed to get User Id, contact admin");
                    forwardToJsp = "forgot_password_security_check.jsp";
                }
            } else {
                session.setAttribute("error", "Fill in all input boxes");
                forwardToJsp = "forgot_password_security_check.jsp";
            }
        } else {
            session.setAttribute("error", "You are already logged in. Please log out first");
            forwardToJsp = "user_loans.jsp";
        }

        return forwardToJsp;
    }

}
