/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Commands.TitleListCommand;
import Commands.UserLoginCommand;
import Commands.UserSignUpCommand;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                    forwardToJsp = "sign_up.jsp";
                    break;
                    
                case "create_user":
                    UserSignUpCommand signup_command = new UserSignUpCommand();
                    forwardToJsp = signup_command.execute(request, response);
                    break;
                    
                case "Home":
                    TitleListCommand list_command = new TitleListCommand();
                    forwardToJsp = list_command.execute(request, response);
                    break;
                case "check_out":
                    System.out.println(request.getAttribute("title_id"));
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
