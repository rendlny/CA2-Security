/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.TitleDao;
import DTO.Title;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class CreateTitleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        
        try {
            String book_title = request.getParameter("book_title");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            int year = Integer.parseInt(request.getParameter("year"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            
            if((book_title != null && !book_title.equals("")) && 
               (author != null && !author.equals("")) &&
               (publisher != null && !publisher.equals(""))
            ) {
                TitleDao titleDao = new TitleDao("library");
                Title title = new Title();
                title.setBook_title(book_title);
                title.setAuthor(author);
                title.setPublisher(publisher);
                title.setYear(year);
                title.setStock(stock);
                
                boolean added = titleDao.createTitle(title);
                
                if(added) {
                    session.setAttribute("notify", "Book '" + book_title + "' added");
                    forwardToJsp = "user_loans.jsp";
                } else {
                    session.setAttribute("error", "A book already exists in the with that title");
                    forwardToJsp = "add_title.jsp";
                }
                
            } else {
                session.setAttribute("error", "Incomplete data, please try again");
                forwardToJsp = "add_title.jsp";
            }
        
        } catch (NumberFormatException ex) {
            session.setAttribute("error", "Only enter numbers for year and stock");
            forwardToJsp = "add_title.jsp";
        }
        
        return forwardToJsp;
    }
    
}
