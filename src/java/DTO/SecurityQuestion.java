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
public class SecurityQuestion {
    private int sq_id;
    private String question;
    
    public SecurityQuestion(){
        sq_id = -1;
        question = null;
    }
    
    public SecurityQuestion(int sq_id, String question){
        this.sq_id = sq_id;
        this.question = question;
    }

    public int getSq_id() {
        return sq_id;
    }

    public void setSq_id(int sq_id) {
        this.sq_id = sq_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.sq_id;
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
        final SecurityQuestion other = (SecurityQuestion) obj;
        if (this.sq_id != other.sq_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SecurityQuestion{" + "sq_id=" + sq_id + ", question=" + question + '}';
    }
    
    
}
