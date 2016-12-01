package DAO;

import DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */

public class UserDao extends Dao implements UserDaoInterface {
    
    
    /**
     * Initialises a UserDao to access the specified database name
     *
     * @param database the name of the database to be accessed
     * Running in local host and listening on port 3306
     */
    public UserDao(String database) {
        super(database);
    }

    /**
     * Adds user information to the database from the {@code User} object supplied
     *
     * @param u {@code User} The object that contains the information describing
     * the user, that will be added to the database
     *
     * @return Boolean flag to determine if the user was successfully added
     *
     */
    @Override
    public boolean addUser(User u) {
        boolean added = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            
            String query = "INSERT INTO user(username, email, pass, salt, "
                    + "f_name, l_name, last_password_change) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getSalt());
            ps.setString(5, u.getF_name());
            ps.setString(6, u.getL_name());
            ps.setString(7, u.getDate());
            
            ps.executeUpdate(); 
            added = true;
        }catch(SQLException e){
             System.out.println("Exception occured in the addUser() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addUser() method: " + e.getMessage());
            }
        }
        return added;
    }

    /**
     * Searches the database for a user with a matching username and password
     *
     * @param username that will be used to find a matching user
     * @param password that will be hashed and matched to the users password
     * 
     * @return An {@code User} Object containing the user details with the 
     * matching username and password
     */
    @Override
    public User login(String username, String password) {
        
        User u = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = getConnection();
            String query = "Select * from user WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) {
                String hash = User.generateSaltedHash(password, rs.getString("salt"));

                if(rs.getString("pass").equals(hash)) {
                    u = new User (
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getString("salt"),
                        rs.getString("f_name"),
                        rs.getString("l_name"),
                        rs.getString("last_password_change"),
                        rs.getBoolean("is_admin")
                    );
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Exception occured in the login() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the login() method: " + e.getMessage());
            }
        }
        
        return u;
    }

    /**
     * Searches the database for a user with a matching username to make sure 
     * username is unique
     *
     * @param username that will be used to find a matching user
     * 
     * @return a boolean to say if a user with the entered username exists
     */
    @Override
    public boolean checkUsername(String username){
        boolean check = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = getConnection();

            String query = "Select * from user WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery(); 
            
            if(!rs.next()){
                check=true;
            }
            
        return check;
        }catch (SQLException e) {
            System.out.println("Exception occured in the checkUsername() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the checkUsername() method: " + e.getMessage());
            }
        }
        return check;
    }

    /**
     * Searches the database for a user with a matching email to make sure 
     * email is unique
     *
     * @param email that will be used to find a matching user
     * 
     * @return a boolean to say if a user with the entered email exists
     */
    @Override
    public boolean checkEmail(String email){
        boolean check = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = getConnection();

            String query = "Select * from user WHERE email = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery(); 
            
            if(!rs.next()){
                check=true;
            }
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the checkEmail() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the checkEmail() method: " + e.getMessage());
            }
        }
        return check;
    }
    
    /**
     * Searches the database for a user with a matching salt to make sure 
     * salt is unique
     *
     * @param salt that will be used to find a matching user
     * 
     * @return a boolean to say if a user with the entered salt exists
     */
    @Override
    public boolean checkSalt(String salt) {
        boolean check = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = getConnection();

            String query = "Select * from user WHERE salt = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, salt);
            rs = ps.executeQuery(); 
            
            if(!rs.next()){
                check=true;
            }
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the checkSalt() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the checkSalt() method: " + e.getMessage());
            }
        }
        return check;
    }
    
    /**
     * Searches the database for a user with a matching username and returns an 
     * {@code ArrayList} of {@code User} Objects
     *
     * @param name that will be used to find users with matching usernames
     * 
     * @return An {@code ArrayList} of {@code User} Objects with matching usernames
     */
    @Override
    public ArrayList<User> searchUsers(String name) {
        ArrayList<User> users = new ArrayList<>();
       
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();

            String query = "SELECT * FROM user WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery(); 
            
            while(rs.next()) {
                User u = new User (
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("pass"),
                    rs.getString("salt"),
                    rs.getString("f_name"),
                    rs.getString("l_name"),
                    rs.getString("last_password_change"),
                    rs.getBoolean("is_admin")
                );
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the searchUsers() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the searchUsers() method: " + e.getMessage());
            }
        }
        return users;
    }

    /**
     * Searches the database for a user with a similar username and returns an 
     * {@code ArrayList} of {@code User} Objects
     *
     * @param name that will be used to find users with similar usernames
     * 
     * @return An {@code ArrayList} of {@code User} Objects with similar usernames
     */
    @Override
    public ArrayList<User> searchUsersLike(String name) {
        ArrayList<User> users = new ArrayList<>();
       
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();

            String query = "Select * from user WHERE username LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1,  "%" + name + "%");
            rs = ps.executeQuery(); 
            
            while(rs.next()){
                User u = new User (
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("pass"),
                    rs.getString("salt"),
                    rs.getString("f_name"),
                    rs.getString("l_name"),
                    rs.getString("last_password_change"),
                    rs.getBoolean("is_admin")
                );
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the searchUsersLike() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the searchUsersLike() method: " + e.getMessage());
            }
        }
        return users;
    }

    @Override
    public boolean updatePassword(String username, String oldPass, String newPass, String salt) {
        boolean updated = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        int affectedRows = 0;
        try{
            con = getConnection();

            String query = "UPDATE user SET pass = ?, salt = ? WHERE username = ? and pass = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, newPass);
            ps.setString(2, salt);
            ps.setString(3, username);
            ps.setString(4, oldPass);
            affectedRows = ps.executeUpdate(); 
            
            if(affectedRows!=0){
                updated=true;
            }
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the updatePassword() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updatePassword() method: " + e.getMessage());
            }
        }
        return updated;
    }
}
