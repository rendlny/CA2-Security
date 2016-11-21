/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Ren
 */
public interface AdminDaoInterface {
    public boolean makeAdmin(String username);
    public boolean deleteUser(String username);
    public boolean revertAdmin(String username);
}
