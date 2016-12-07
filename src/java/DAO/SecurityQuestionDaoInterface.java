/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SecurityQuestion;
import java.util.ArrayList;

/**
 *
 * @author Ren
 */
public interface SecurityQuestionDaoInterface {
    public ArrayList<SecurityQuestion> getAllSecurityQuestions();
    public SecurityQuestion getUsersSecurityQuestion(int sq_id);
}
