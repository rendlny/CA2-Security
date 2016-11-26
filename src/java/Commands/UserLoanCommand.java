/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.LoanDao;
import DTO.Loan;
import DTO.User;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class UserLoanCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        LoanDao loanDao = new LoanDao("library");
        
        User logged_in = (User)session.getAttribute("logged_in");
        
        if(logged_in != null) {
            ArrayList<Loan> loans = loanDao.listUserLoans(logged_in.getUser_id());
            session.setAttribute("user_loans", loans);
            forwardToJsp = "user_loans.jsp";
        } else {
            session.setAttribute("error", "You have to be logged in to use this functionality");
            forwardToJsp = "login.jsp";
        }
        
        return forwardToJsp;
    }
    
}
