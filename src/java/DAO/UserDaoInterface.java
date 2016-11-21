/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.User;
import java.util.ArrayList;
/**
 *
 * @author Ren
 */
public interface UserDaoInterface {
    public boolean addUser(User u);
    public User login(String username, String password);
    public boolean checkUsername(String username);
    public boolean checkEmail(String email);
    public boolean checkSalt(String salt);
    public ArrayList<User> searchUsers(String name);
    public ArrayList<User> searchUsersLike(String name);
}
