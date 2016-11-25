/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.TitleDao;
import DTO.Title;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class TitleListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TitleDao titleDao = new TitleDao("library");
        
        ArrayList<Title> titles = titleDao.listAllBooks();
        
        HttpSession session = request.getSession();
        
        session.setAttribute("all_titles", titles);
        
        return "home.jsp";
    }
    
}
