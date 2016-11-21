/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Loan;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Conno
 */
public class LoanDaoTest {
    
    private static String test_database = "test_library";
    
    public LoanDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkOutBook method, of class LoanDao.
     */
    @Test
    public void testCheckOutBook() {
        System.out.println("checkOutBook");
        
        LoanDao instance = new LoanDao(test_database);
        
        Loan l = new Loan();
        l.setTitle_id(2);
        l.setUser_id(8);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();

        String date = dateFormat.format(cal.getTime());
        
        cal.add(Calendar.DAY_OF_YEAR, 7);
        
        String return_date = dateFormat.format(cal.getTime());
        
        l.setWithdraw_date(date);
        l.setReturn_by_date(return_date);
        boolean expResult = true;
        
        boolean result = instance.checkOutBook(l);
        assertEquals(expResult, result);
        
        ArrayList<Loan> userLoans = instance.listUserLoans(8);
        instance.returnBook(userLoans.get(0).getLoan_id());
    }

    /**
     * Test of returnBook method, of class LoanDao.
     */
    @Test
    public void testReturnBook() {
        System.out.println("returnBook");
        
        LoanDao instance = new LoanDao(test_database);
        
        Loan l = new Loan();
        l.setTitle_id(2);
        l.setUser_id(8);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();

        String date = dateFormat.format(cal.getTime());
        
        cal.add(Calendar.DAY_OF_YEAR, 7);
        
        String return_date = dateFormat.format(cal.getTime());
        
        l.setWithdraw_date(date);
        l.setReturn_by_date(return_date);
        
        instance.checkOutBook(l);
        
        int loan_id = instance.listUserLoans(8).get(0).getLoan_id();
        boolean expResult = true;
        
        boolean result = instance.returnBook(loan_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of countCurrentlyOnLoan method, of class LoanDao.
     */
    @Test
    public void testCountCurrentlyOnLoan() {
        System.out.println("countCurrentlyOnLoan");
        
        LoanDao instance = new LoanDao(test_database);
        
        int title_id = 2;
        int expResult = 1;
        
        int result = instance.countCurrentlyOnLoan(title_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of currentUserLoanCount method, of class LoanDao.
     */
    @Test
    public void testCurrentUserLoanCount() {
        System.out.println("currentUserLoanCount");
        
        LoanDao instance = new LoanDao(test_database);
        
        int user_id = 8;
        int expResult = 1;
        
        int result = instance.currentUserLoanCount(user_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of listUserLoans method, of class LoanDao.
     */
    @Test
    public void testListUserLoans() {
        System.out.println("listUserLoans");
        
        LoanDao instance = new LoanDao(test_database);
        
        int user_id = 8;
        int expResult = 1;
        
        ArrayList<Loan> result = instance.listUserLoans(user_id);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of discontinueLoan method, of class LoanDao.
     */
    @Test
    public void testDiscontinueLoan() {
        System.out.println("discontinueLoan");
        
        LoanDao instance = new LoanDao(test_database);
        
        Loan l = new Loan();
        l.setTitle_id(3);
        l.setUser_id(8);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();

        String date = dateFormat.format(cal.getTime());
        
        cal.add(Calendar.DAY_OF_YEAR, 7);
        
        String return_date = dateFormat.format(cal.getTime());
        
        l.setWithdraw_date(date);
        l.setReturn_by_date(return_date);
        
        instance.checkOutBook(l);
        
        int title_id = 3;
        boolean expResult = true;
        boolean result = instance.discontinueLoan(title_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of loanCheck method, of class LoanDao.
     */
    @Test
    public void testLoanCheck() {
        System.out.println("loanCheck");
        
        LoanDao instance = new LoanDao(test_database);
        
        Loan l = new Loan();
        l.setTitle_id(3);
        l.setUser_id(8);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();

        String date = dateFormat.format(cal.getTime());
        
        cal.add(Calendar.DAY_OF_YEAR, 7);
        
        String return_date = dateFormat.format(cal.getTime());
        
        l.setWithdraw_date(date);
        l.setReturn_by_date(return_date);
        
        instance.checkOutBook(l);
        
        int user_id = 8;
        int title_id = 3;
        
        boolean expResult = true;
        boolean result = false;
        
        Loan loan = instance.loanCheck(user_id, title_id);
        
        if(loan != null) {
            result = true;
        }
        
        assertEquals(expResult, result);
        instance.deleteLoan(loan.getLoan_id());
    }

    /**
     * Test of deleteLoan method, of class LoanDao.
     */
    @Test
    public void testDeleteLoan() {
        System.out.println("deleteLoan");LoanDao instance = new LoanDao(test_database);
        
        Loan l = new Loan();
        l.setTitle_id(3);
        l.setUser_id(8);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();

        String date = dateFormat.format(cal.getTime());
        
        cal.add(Calendar.DAY_OF_YEAR, 7);
        
        String return_date = dateFormat.format(cal.getTime());
        
        l.setWithdraw_date(date);
        l.setReturn_by_date(return_date);
        
        instance.checkOutBook(l);
        
        int user_id = 8;
        int title_id = 3;
        
        Loan loan = instance.loanCheck(user_id, title_id);
        
        int loan_id = loan.getLoan_id();
        boolean expResult = true;
        
        boolean result = instance.deleteLoan(loan_id);
        assertEquals(expResult, result);
    }
    
}
