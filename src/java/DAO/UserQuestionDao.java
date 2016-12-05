/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ren
 */
public class UserQuestionDao extends Dao implements UserQuestionDaoInterface {

    public UserQuestionDao(String database) {
        super(database);
    }

    @Override
    public String getUserQuestionAnswer(int question_id, int user_id) {
        String answer = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT answer FROM user_questions WHERE sq_id = ? AND user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, question_id);
            ps.setInt(2, user_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                answer = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserQuestionAnswer() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserQuestionAnswer() method: " + e.getMessage());
            }
        }

        return answer;
    }

    @Override
    public boolean addUserQuestionAnswer(int question_id, int user_id, String answer) {
        boolean added = false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            String query = "INSERT INTO user_questions(sq_id, user_id, answer) VALUES(?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, question_id);
            ps.setInt(2, user_id);
            ps.setString(3, answer);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                added = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the addUserQuestionAnswer() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addUserQuestionAnswer() method: " + e.getMessage());
            }
        }

        return added;

    }

    @Override
    public boolean removeUserQuestionAnswer(int question_id, int user_id) {
        boolean removed = false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            String query = "DELETE FROM user_questions WHERE sq_id = ? and user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, question_id);
            ps.setInt(2, user_id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                removed = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the removeUserQuestionAnswer() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the removeUserQuestionAnswer() method: " + e.getMessage());
            }
        }

        return removed;
    }

    @Override
    public boolean updateAnswerUserQuestionAnswer(int question_id, int user_id, String answer) {
        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            
            String query = "UPDATE user_questions SET answer = ? WHERE sq_id = ? and user_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, answer);
            ps.setInt(1, question_id);
            ps.setInt(1, user_id);
            
            int rows = ps.executeUpdate(); 
            
            if(rows>0){
                updated = true;
            }
        }catch(SQLException e){
             System.out.println("Exception occured in the updateUserQuestionAnswer() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateUserQuestionAnswer() method: " + e.getMessage());
            }
        }
        return updated;
    }

}
