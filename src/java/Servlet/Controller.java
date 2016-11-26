/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Commands.TitleCheckOutCommand;
import Commands.TitleListCommand;
import Commands.UserLoanCommand;
import Commands.UserLoginCommand;
import Commands.UserSignUpCommand;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        session.setAttribute("error", null);
        String forwardToJsp = null;
        String action = request.getParameter("action");

        switch(action){
                case "Login":
                    forwardToJsp = "login.jsp";
                    break;
                    
                case "login_user":
                    UserLoginCommand login_command = new UserLoginCommand();
                    forwardToJsp = login_command.execute(request, response);
                    break;
                    
                case "Sign Up":
                    if(session.getAttribute("logged_in") == null) {
                        forwardToJsp = "sign_up.jsp";
                    } else {
                        session.setAttribute("error", "You are already logged in. Please sing out first.");
                        forwardToJsp = "home.jsp";
                    }
                    break;
                    
                case "create_user":
                    UserSignUpCommand signup_command = new UserSignUpCommand();
                    forwardToJsp = signup_command.execute(request, response);
                    break;
                    
                case "Home":
                    if(session.getAttribute("logged_in") != null) {
                        TitleListCommand list_command = new TitleListCommand();
                        forwardToJsp = list_command.execute(request, response);
                    } else {
                        session.setAttribute("error", "You have to be logged in to use this functionalit. "
                                + "Please try again");
                        forwardToJsp = "login.jsp";
                    }
                    break;
                    
                case "check_out":
                    TitleCheckOutCommand checkOut_command = new TitleCheckOutCommand();
                    forwardToJsp = checkOut_command.execute(request, response);
                    break;
                    
                case "user_loans":
                    if(session.getAttribute("logged_in") != null) {
                        UserLoanCommand userLoan_command = new UserLoanCommand();
                        forwardToJsp = userLoan_command.execute(request, response);
                    } else {
                        session.setAttribute("error", "You have to be logged in to use this functionality. "
                                + "Please try again");
                        forwardToJsp = "login.jsp";
                    }
                    break;
                    
                case "profile":
                    if(session.getAttribute("logged_in") != null) {
                        forwardToJsp = "profile.jsp";
                    } else {
                        session.setAttribute("error", "You have to be logged in to use this functionality.");
                        forwardToJsp = "login.jsp";
                    }
                    break;
                    
                case "logout":
                    session.setAttribute("logged_in", null);
                    forwardToJsp = "login.jsp";
                    break;
                    
                default:
                    forwardToJsp = "index.jsp";
                    break;
        }
            
        response.sendRedirect(forwardToJsp);
          
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
