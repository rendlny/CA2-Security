/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Title;
import java.util.ArrayList;
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
public class TitleDaoTest {
    
    private static String test_database = "test_library";
    
    public TitleDaoTest() {
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
     * Test of createTitle method, of class TitleDao.
     */
    @Test
    public void testCreateTitle() {
        System.out.println("createTitle");
        
        TitleDao instance = new TitleDao(test_database);
        
        Title t = new Title();
        t.setBook_title("Test_Novel");
        t.setAuthor("Test_Author");
        t.setPublisher("Test_Publisher");
        t.setYear(2016);
        t.setStock(1);
        
        boolean expResult = true;
        
        boolean result = instance.createTitle(t);
        assertEquals(expResult, result);
        
        instance.deleteTitle(instance.searchByName("Test_Novel").get(0).getTitle_id());
    }

    /**
     * Test of getTitleById method, of class TitleDao.
     */
    @Test
    public void testGetTitleById() {
        System.out.println("getTitleById");
        
        TitleDao instance = new TitleDao(test_database);
        
        int title_id = 2;
        
        boolean expResult = true;
        boolean result = false;
        
        Title found = instance.getTitleById(title_id);
        if(found != null) {
            result = true;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of editTitle method, of class TitleDao.
     */
    @Test
    public void testEditTitle() {
        System.out.println("editTitle");
        TitleDao instance = new TitleDao(test_database);
        
        Title t = new Title(2, "Harry P's Stone",
                "J.K.Rowling", "Bloomsbury", 1997, 15);
        
        boolean expResult = true;
        boolean result = instance.editTitle(t);
        assertEquals(expResult, result);
        
        t = new Title(2, "Harry Potter and the Philosopher's Stone",
            "J.K.Rowling", "Bloomsbury", 1997, 15);
        
        instance.editTitle(t);
    }

    /**
     * Test of deleteTitle method, of class TitleDao.
     */
    @Test
    public void testDeleteTitle() {
        System.out.println("deleteTitle");
        TitleDao instance = new TitleDao(test_database);
        
        Title t = new Title();
        t.setBook_title("Test_Novel");
        t.setAuthor("Test_Author");
        t.setPublisher("Test_Publisher");
        t.setYear(2016);
        t.setStock(1);
        
        instance.createTitle(t);
        
        int title_id = instance.searchByName("Test_Novel").get(0).getTitle_id();
        
        boolean expResult = true;
        boolean result = instance.deleteTitle(title_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchByName method, of class TitleDao.
     */
    @Test
    public void testSearchByName() {
        System.out.println("searchByName");
        
        TitleDao instance = new TitleDao(test_database);
        
        String name = "Harry Potter and the Philosopher's Stone";
        int expResult = 1;
        
        ArrayList<Title> result = instance.searchByName(name);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchByNameLike method, of class TitleDao.
     */
    @Test
    public void testSearchByNameLike() {
        System.out.println("searchByNameLike");
        
        TitleDao instance = new TitleDao(test_database);
        
        String name = "Harry Potter";
        int expResult = 1;
        
        ArrayList<Title> result = instance.searchByNameLike(name);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of listAllBooks method, of class TitleDao.
     */
    @Test
    public void testListAllBooks() {
        System.out.println("listAllBooks");
        
        TitleDao instance = new TitleDao(test_database);
        
        int expResult = 2;
        
        ArrayList<Title> result = instance.listAllBooks();
        assertEquals(expResult, result.size());
    }
    
}
