/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserQuestion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ren
 */
public class UserQuestionDao extends Dao implements UserQuestionDaoInterface {

    /**
     * Initialises a UserQuestionDao to access the specified database name
     * @param database the name of the database to be accessed
     */
    public UserQuestionDao(String database) {
        super(database);
    }

    /**
     *
     * @param question_id used to find user question with this id
     * @param user_id used to find user question with this user id
     * @return a String which is the answer from the UserQuestion object that has both the user_id and question_id
     */
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

    /**
     *
     * @param question_id id of the UserQuestion
     * @param user_id id of the user whom this UserQuestion belongs to
     * @param answer string which is the User answers to the question hashed with a unique salt
     * @param salt String, the unique salt used to hash the user's answer
     * @return a boolean of true if the Object is added to the db otherwise false
     */
    @Override
    public boolean addUserQuestionAnswer(int question_id, int user_id, String answer, String salt) {
        boolean added = false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            String query = "INSERT INTO user_questions(sq_id, user_id, answer, salt) VALUES(?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, question_id);
            ps.setInt(2, user_id);
            ps.setString(3, answer);
            ps.setString(4, salt);

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

    /**
     * removes a UserQuestion from the db if it finds a match that has both parameters
     * @param question_id id of the question the user used
     * @param user_id id of the user whom this question belongs to
     * @return a boolean of true if the Object is removed from the db otherwise false
     */
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

    /**
     *
     * @param question_id the new int that will replace the old question_id on the db
     * @param user_id the new int that will replace the old user_id on the db
     * @param answer the new string that will replace the old answer on the db
     * @return a  boolean of true if the Object is updated on the db otherwise false
     */
    @Override
    public boolean updateAnswerUserQuestionAnswer(int question_id, int user_id, String answer) {
        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE user_questions SET answer = ? WHERE sq_id = ? and user_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, answer);
            ps.setInt(1, question_id);
            ps.setInt(1, user_id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                updated = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateUserQuestionAnswer() method: " + e.getMessage());
        } finally {
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

    /**
     *
     * @param user_id used to find the ids of the security questions the user used
     * @return an array of int which are the ids for which security questions the user used
     */
    @Override
    public int[] getUserQuestionsIds(int user_id) {
        int[] question_ids = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT sq_id FROM user_questions WHERE user_id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();

            question_ids = new int[3];

            int i = 0;
            while (rs.next()) {
                question_ids[i] = rs.getInt("sq_id");
                i++;
            }

        } catch (SQLException ex) {
            System.out.println("Exception occured in the finally section of"
                    + " the getUserQuestionsIds() method: " + ex.getMessage());
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
                        + " the getUserQuestionsIds() method: " + e.getMessage());
            }
        }

        return question_ids;
    }

    /**
     *
     * @param user_id to find the users answers on UserQuestions
     * @return an array of strings which are the user's hashed answers
     */
    @Override
    public String[] getUserQuestionAnswers(int user_id) {
        String[] question_answers = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT answer FROM user_questions WHERE user_id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();

            question_answers = new String[3];

            int i = 0;
            while (rs.next()) {
                question_answers[i] = rs.getString("answer");
                i++;
            }

        } catch (SQLException ex) {
            System.out.println("Exception occured in the finally section of"
                    + " the getUserQuestionAnswers() method: " + ex.getMessage());
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
                        + " the getUserQuestionAnswers() method: " + e.getMessage());
            }
        }

        return question_answers;
    }

    /**
     *
     * @param user_id used to find all of a users USerQUestions
     * @return an ArrayList of UserQuestions that have the user id given
     */
    @Override
    public ArrayList<UserQuestion> getUserQuestionByUserId(int user_id) {
        ArrayList<UserQuestion> userQuestions = null;
        UserQuestion q = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM user_questions WHERE user_id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();

            userQuestions = new ArrayList<>();
            while (rs.next()) {
                q = new UserQuestion(
                        rs.getInt("sq_id"),
                        rs.getInt("user_id"),
                        rs.getString("answer"),
                        rs.getString("salt")
                );
                userQuestions.add(q);
            }

        } catch (SQLException ex) {
            System.out.println("Exception occured in the finally section of"
                    + " the getUserQuestionByUserId() method: " + ex.getMessage());
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
                        + " the getUserQuestionByUserId() method: " + e.getMessage());
            }
        }

        return userQuestions;
    }

}
