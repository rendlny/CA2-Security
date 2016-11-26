/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.LoanDao;
import DAO.TitleDao;
import DTO.Loan;
import DTO.Title;
import DTO.User;
import java.util.InputMismatchException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class TitleCheckOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        HttpSession session = request.getSession();
        
        if(session.getAttribute("logged_in") == null) {
            session.setAttribute("error", "You Have to be logged in to use this function");
            forwardToJsp = "login.jsp";
        } else {
            
            boolean title_check = false;
            int title_id = -1;
            try {
                title_id = Integer.parseInt(request.getParameter("title_id"));
            } catch (InputMismatchException ex) {
                session.setAttribute("error", "An invalid title ID was detected. Please try again.");
                forwardToJsp = "home.jsp";
                title_check = true;
            }
            
            TitleDao titleDao = new TitleDao("library");
            Title title = titleDao.getTitleById(title_id);
            
            if(title == null) {
                title_check = true;
            }
            
            
            
            if(!title_check) {
                
                LoanDao loanDao = new LoanDao("library");
                User logged_in = (User)session.getAttribute("logged_in");
                
                if(loanDao.currentUserLoanCount(logged_in.getUser_id()) < 3) {
                    
                    if(loanDao.countCurrentlyOnLoan(title_id) < title.getStock()) {
                        Loan newLoan = new Loan();
                        newLoan.setTitle_id(title_id);
                        newLoan.setUser_id(logged_in.getUser_id());
                        newLoan.setWithdraw_date(Loan.getCurrentDate());
                        newLoan.setReturn_by_date(Loan.calcReturnDate());
                        
                        if(loanDao.checkOutBook(newLoan)) {
                            forwardToJsp = "Controller?action=user_loans";
                        } else {
                            session.setAttribute("error", "An error occured when finalizing the loan. Please try again later");
                            forwardToJsp = "home.jsp";
                        }
                        
                    } else {
                        session.setAttribute("error", "Apologies, it seems this book is out of stock. "
                            + "Please check again later");
                        forwardToJsp = "home.jsp";
                    }
                } else {
                    session.setAttribute("error", "You have already hit your limit on loans.\n"
                        + "Please return some before continuing");
                    forwardToJsp = "user_loans.jsp";
                }
            }
        }
                
        return forwardToJsp;
    }
    
}
