/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Loan;
import DTO.Title;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public class LoanDao extends Dao implements LoanDaoInterface {
    
    public LoanDao(String database) {
        super(database);
    }
    
    /**
     *
     * @param l {@code Loan} Object to be inserted into loan table on DB
     * @return a boolean of whether it was inserted successfully or not
     */
    @Override
    public boolean checkOutBook(Loan l){
        boolean checkedOut = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            
            String query = "INSERT INTO loan(user_id, "
                    + "title_id, "
                    + "withdraw_date, "
                    + "return_by_date, "
                    + "is_returned) "
                    + "VALUES(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, l.getUser_id());
            ps.setInt(2, l.getTitle_id());
            ps.setString(3, l.getWithdraw_date());
            ps.setString(4, l.getReturn_by_date());
            ps.setBoolean(5, l.isIs_returned());
            
            ps.executeUpdate(); 
            checkedOut = true;
        }catch(SQLException e){
             System.out.println("Exception occured in the checkOutBook() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the checkOutBook() method: " + e.getMessage());
            }
        }
        return checkedOut;
    }

    /**
     * Call when a book is being returned to update column returned in DB to be true
     * @param l {@code Loan} Object used to update the loan and set it as returned
     * @return boolean on whether the book was returned successfully
     */
    @Override
    public boolean returnBook(int loan_id){
        boolean returned = false;
         Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            
            String query = "UPDATE loan SET is_returned = 1 WHERE loan_id = ? AND is_returned = 0";
            ps = con.prepareStatement(query);
            ps.setInt(1, loan_id);
            
            ps.executeUpdate(); 
            returned = true;
        }catch(SQLException e){
             System.out.println("Exception occured in the returnBook() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the returnBook() method: " + e.getMessage());
            }
        }
        return returned;
    }

    /**
     * counts the number of a specific book that is currently on loan
     * @param title_id the id of the book
     * @return int of the number of the book currently on loan
     */
    @Override
    public int countCurrentlyOnLoan(int title_id){
        int count = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            
            String query = "SELECT count(title_id) FROM loan WHERE title_id = ? AND is_returned = 0";
            ps = con.prepareStatement(query);
            ps.setInt(1, title_id);
            rs = ps.executeQuery();
            if(rs.next()){ 
                count=rs.getInt(1);
            }
        }catch(SQLException e){
             System.out.println("Exception occured in the countCurrentlyOnLoan() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the countCurrentlyOnLoan() method: " + e.getMessage());
            }
        }
        return count;
    }

    /**
     * Counts the number of loans of user currently has
     * @param user_id used to find the users current loans
     * @return returns an int , the number of books the user currently has on loan
     */
    @Override
    public int currentUserLoanCount(int user_id){
        
        int count = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            
            String query = "SELECT count(title_id) FROM loan "
                    + "WHERE user_id = ? AND is_returned = 0";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery(); 
            
            if(rs.next()) {
                count=rs.getInt(1);
            }
            
        }catch(SQLException e){
             System.out.println("Exception occured in the countCurrentlyOnLoan() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the countCurrentlyOnLoan() method: " + e.getMessage());
            }
        }
        return count;
    }
    
    /**
     * Gets the currently checked out books by the user using user id
     * 
     * @param user_id used to find matching books on loan to user
     * @return {@code ArrayList} of {@code Title} Objects
     */
    @Override
    public ArrayList<Loan> listUserLoans(int user_id) {
        
        ArrayList<Loan> myLoans = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();

            String query = "SELECT * FROM loan WHERE user_id = ? AND is_returned = 0 ";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id );
            rs = ps.executeQuery(); 
            
            while(rs.next()){
                Loan l = new Loan(
                    rs.getInt("loan_id"), 
                    rs.getInt("title_id"), 
                    rs.getInt("user_id"), 
                    rs.getString("withdraw_date"),
                    rs.getString("return_by_date"), 
                    rs.getBoolean("is_returned")
                );
                myLoans.add(l);
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
        
        return myLoans;
    }

    /**
     * used to when a book is deleted, deletes all loans of that book
     * @param title_id used to find which loans have the deleted book
     * @return a boolean on whether the loans where deleted
     */
    @Override
    public boolean discontinueLoan(int title_id) {
        boolean returned = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            
            String query = "UPDATE loan SET is_returned = 1 WHERE title_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, title_id);
            
            ps.executeUpdate(); 
            returned = true;
        }catch(SQLException e){
             System.out.println("Exception occured in the discontinueLoan() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the returnBook() method: " + e.getMessage());
            }
        }
        return returned;
    }

    /**
     * uses user id and title id to check if a loan exists 
     * @param user_id used to find the users loan
     * @param title_id used to find the users loan that has a specific book
     * @return The Loan object
     */
    @Override
    public Loan loanCheck(int user_id, int title_id) {
        Loan l = null;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = getConnection();
            
            String query = "SELECT * FROM loan WHERE user_id = ? "
                    + "AND title_id = ? "
                    + "AND is_returned = 0";
            
            ps = con.prepareStatement(query);
            
            ps.setInt(1, user_id);
            ps.setInt(2, title_id);
            
            rs = ps.executeQuery();
            
            if(rs.next()) {
                l = new Loan(
                        rs.getInt("loan_id"),
                        rs.getInt("user_id"),
                        rs.getInt("title_id"),
                        rs.getString("withdraw_date"),
                        rs.getString("return_by_date"),
                        rs.getBoolean("is_returned")
                );
            }
            
        } catch (SQLException ex) {
            System.out.println("Exception occured in the finally section of"
                        + " the loanCheck() method: " + ex.getMessage());
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the loanCheck() method: " + e.getMessage());
            }
        }
        
        
        return l;
    }

    /**
     * deletes a specific row from the loan table
     * @param loan_id to get the specific loan row to be deleted
     * @return a boolean on whether it was deleted or not
     */
    @Override
    public boolean deleteLoan(int loan_id){
        
        boolean deleted = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = getConnection();
            
            String query = "DELETE FROM loan WHERE loan_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, loan_id);
            int affected_rows = ps.executeUpdate(); 
            
            if(affected_rows > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteLoan()"
                    + " method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the deleteLoan() method: " + e.getMessage());
            }
        }
        return deleted;
    }
}
