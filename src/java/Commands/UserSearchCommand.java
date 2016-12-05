/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDao;
import DTO.User;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class UserSearchCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        
        String search_critera = request.getParameter("search");
        
        if(search_critera != null && !search_critera.equals("")) {
            
            UserDao userDao = new UserDao("library");
            ArrayList<User> found = new ArrayList<>();
            
            ArrayList<User> temp = userDao.searchUsers(search_critera);
            
            for(User u : temp) {
                found.add(u);
            }
            
            temp = userDao.searchUsersLike(search_critera);
            
            for(User u : temp) {
                boolean check = false;
                
                int index = 0;
                while(!check) {
                    
                    User u2 = temp.get(index);
                    if(u.getUser_id() == u2.getUser_id()) {
                        check = true;
                    }
                }
                
                if(!check) {
                    found.add(u);
                }
            }
            
            session.setAttribute("found_users", found);
            forwardToJsp = "search.jsp";
            
        } else {
            session.setAttribute("error", "Cannot search users with no vlaue");
            forwardToJsp = "search.jsp";
        }
        
        return forwardToJsp;
    }
    
}
