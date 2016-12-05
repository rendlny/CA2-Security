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
public interface UserQuestionDaoInterface {
    public String getUserQuestionAnswer(int question_id, int user_id);
    public boolean addUserQuestionAnswer(int question_id, int user_id, String answer);
    public boolean removeUserQuestionAnswer(int question_id, int user_id);
    public boolean updateAnswerUserQuestionAnswer(int question_id, int user_id, String answer);
}
