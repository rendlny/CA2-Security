/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SecurityQuestion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ren
 */
public class SecurityQuestionDao extends Dao implements SecurityQuestionDaoInterface {

    public SecurityQuestionDao(String database) {
        super(database);
    }

    @Override
    public ArrayList<SecurityQuestion> getAllSecurityQuestions() {
        ArrayList<SecurityQuestion> questions = new ArrayList<>();
        SecurityQuestion sq = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM security_questions";

            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                sq = new SecurityQuestion(
                        rs.getInt("sq_id"),
                        rs.getString("question")
                );
                questions.add(sq);
            }

        } catch (SQLException ex) {
            System.out.println("Exception occured in the finally section of"
                    + " the getAllSecurityQuestions() method: " + ex.getMessage());
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
                System.out.println("Exception occured in the finally section of"
                        + " the getAllSecurityQuestions() method: " + e.getMessage());
            }
        }

        return questions;
    }

    @Override
    public SecurityQuestion getUsersSecurityQuestion(int id) {
        SecurityQuestion sq = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM security_questions WHERE sq_id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                sq = new SecurityQuestion(
                        rs.getInt("sq_id"),
                        rs.getString("question")
                );
            }

        } catch (SQLException ex) {
            System.out.println("Exception occured in the finally section of"
                    + " the getUsersSecurityQuestions() method: " + ex.getMessage());
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
                System.out.println("Exception occured in the finally section of"
                        + " the getUsersSecurityQuestions() method: " + e.getMessage());
            }
        }

        return sq;
    }

}
