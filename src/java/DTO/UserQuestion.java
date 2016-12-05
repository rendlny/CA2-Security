/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Ren
 */
public class UserQuestion {
    private int sq_id;
    private int user_id;
    private String answer;
    
    public UserQuestion(){
        sq_id = -1;
        user_id = -1;
        answer = null;
    }
    
    public UserQuestion(int sq_id, int user_id, String answer){
        this.sq_id = sq_id;
        this.user_id = user_id;
        this.answer = answer;
    }

    public int getSq_id() {
        return sq_id;
    }

    public void setSq_id(int sq_id) {
        this.sq_id = sq_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.sq_id;
        hash = 37 * hash + this.user_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserQuestion other = (UserQuestion) obj;
        if (this.sq_id != other.sq_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserQuestion{" + "sq_id=" + sq_id + ", user_id=" + user_id + ", answer=" + answer + '}';
    }
}
