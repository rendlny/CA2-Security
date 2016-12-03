package Commands;

import DAO.TitleDao;
import DTO.Title;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class TitleSearchCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        
        String search_critera = request.getParameter("search");
        
        if(search_critera != null && !search_critera.equals("")) {
            
            TitleDao titleDao = new TitleDao("library");
            
            ArrayList<Title> titles = titleDao.searchByName(search_critera);
            
            session.setAttribute("found_titles", titles);
            forwardToJsp = "search.jsp";
            
        } else {
            session.setAttribute("error", "Cannot search titles with no value");
            forwardToJsp = "search.jsp";
        }
        
        
        return forwardToJsp;
    }
    
}
