/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserQuestionDao;
import DTO.User;
import DTO.UserQuestion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class UpdateUserQuestionAnswerCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        if (session.getAttribute("logged_in") != null) {
            User user = (User) session.getAttribute("logged_in");
            String answer = request.getParameter("sq");
            int q_id = Integer.parseInt(request.getParameter("q_id"));
            if (user != null && answer != null) {

                String saltedAnswer = null;
                boolean check = false;
                String salt = null;
                UserQuestion userQ = new UserQuestion();
                UserQuestionDao userQDao = new UserQuestionDao("library");
                do {
                    check = false;

                    salt = userQ.generateSalt();

                    if (userQDao.checkSalt(salt)) {
                        //user.setSalt(salt);
                        saltedAnswer = User.generateSaltedHash(answer, salt);
                    } else {
                        check = true;
                    }

                } while (check);

                boolean updated = userQDao.updateAnswerUserQuestionAnswer(q_id, user.getUser_id(), saltedAnswer);
                if (updated == true) {
                    session.setAttribute("notify", "Answer updated successfully");
                    forwardToJsp = "profile.jsp";
                } else {
                    session.setAttribute("error", "Failed to update answer, contact admin");
                    forwardToJsp = "profile.jsp";
                }
            } else {
                session.setAttribute("error", "Empty input box, please fill in and try again");
                forwardToJsp = "profile.jsp";
            }

        } else {
            session.setAttribute("error", "You must be logged in to edit answers");
            forwardToJsp = "login.jsp";
        }
        return forwardToJsp;
    }
}
