/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.LoanDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class TitleReturnCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;

        String loan_id = request.getParameter("loan_id");

        if (loan_id != null && !loan_id.equals("")) {
            try {
                int id = Integer.parseInt(loan_id);
                LoanDao loanDao = new LoanDao("library");

                boolean returned = loanDao.returnBook(id);
                if (returned == true) {
                    session.setAttribute("notify", "<p>Book successfully returned</p>");
                    forwardToJsp = "user_loans.jsp";
                } else {
                    session.setAttribute("error", "Could not return book, contact admin");
                    forwardToJsp = "user_loans.jsp";
                }
            } catch (NumberFormatException ex) {
                session.setAttribute("error", "Title ID must be an int");
                forwardToJsp = "user_loans.jsp";
            }
        } else {
            session.setAttribute("error", "Cannot return a title without title ID");
            forwardToJsp = "user_loans.jsp";
        }

        return forwardToJsp;
    }

}
