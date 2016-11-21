/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ren
 */
public class AdminDao extends UserDao implements AdminDaoInterface{
    
    /**
     * Initialises a AdminDao to access the specified database name
     * @param database the name of the database to be accessed
     * Running in localhost and listening on port 3306
     */
    public AdminDao(String database) {
        super(database);
    }
    
    /**
     *
     * @param username will be used to find a user
     * @return a boolean that will be true if the use is found and is set to admin
     */
    @Override
    public boolean makeAdmin(String username){
        boolean admin = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            
            String query = "UPDATE user SET is_admin = 1 WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            int result = ps.executeUpdate(); 
            
            if(result == 1) {
                admin = true;
            }
            
        }catch(SQLException e){
             System.out.println("Exception occured in the makeAdmin() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the makeAdmin() method: " + e.getMessage());
            }
        }
        return admin;
    }
    
    /**
     *
     * @param username will be used to find a user
     * @return a boolean that will be true if the user is found and they are not an admin and are then deleted
     */
    @Override
    public boolean deleteUser(String username){
        boolean deleted = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            
            String query = "DELETE FROM user WHERE username = ? AND is_admin = 0";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            int affected_rows = ps.executeUpdate();
            if(affected_rows == 1) {
                deleted = true;
            }
        }catch(SQLException e){
             System.out.println("Exception occured in the deleteUser() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the deleteUser() method: " + e.getMessage());
            }
        }
        return deleted;
    }
    
    /**
     *
     * @param username will be used to find a user
     * @return boolean returns true if user is found and changed to a normal user 
     */
    @Override
    public boolean revertAdmin(String username){
        boolean admin = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            
            String query = "UPDATE user SET is_admin = 0 WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.executeUpdate(); 
            admin = true;
            
        }catch(SQLException e){
             System.out.println("Exception occured in the makeAdmin() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the makeAdmin() method: " + e.getMessage());
            }
        }
        return admin;
    }
}
