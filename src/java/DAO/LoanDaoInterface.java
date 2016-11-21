/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Loan;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public interface LoanDaoInterface {
    public boolean checkOutBook(Loan l);
    public boolean returnBook(int loan_id);
    public int countCurrentlyOnLoan(int title_id);
    public int currentUserLoanCount(int user_id);
    public ArrayList<Loan> listUserLoans(int user_id);
    public boolean discontinueLoan(int title_id);
    public Loan loanCheck(int user_id, int title_id);
    public boolean deleteLoan(int loan_id);
}
