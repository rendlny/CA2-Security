/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Conno
 */
public class Loan {
    
    private final int loan_id;
    private int title_id;
    private int user_id;
    private String withdraw_date; 
    private String return_by_date;
    private boolean is_returned;
    
    public Loan() {
        loan_id = -1;
        title_id = -1;
        user_id = -1;
        withdraw_date = null;
        return_by_date = null;
        is_returned = false;
    }

    public Loan(int loan_id, int title_id, int user_id, String withdraw_date, 
                String return_by_date, boolean is_returned) 
    {
        this.loan_id = loan_id;
        this.title_id = title_id;
        this.user_id = user_id;
        this.withdraw_date = withdraw_date;
        this.return_by_date = return_by_date;
        this.is_returned = is_returned;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getWithdraw_date() {
        return withdraw_date;
    }

    public void setWithdraw_date(String withdraw_date) {
        this.withdraw_date = withdraw_date;
    }

    public String getReturn_by_date() {
        return return_by_date;
    }

    public void setReturn_by_date(String return_by_date) {
        this.return_by_date = return_by_date;
    }

    public boolean isIs_returned() {
        return is_returned;
    }

    public void setIs_returned(boolean is_returned) {
        this.is_returned = is_returned;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.loan_id;
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
        final Loan other = (Loan) obj;
        if (this.loan_id != other.loan_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Loan{" + "loan_id=" + loan_id + ", title_id=" + title_id 
                    + ", user_id=" + user_id + ", withdraw_date=" + withdraw_date 
                    + ", return_by_date=" + return_by_date 
                    + ", is_returned=" + is_returned + '}';
    }
}
