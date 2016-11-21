/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
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
public class AdminDaoTest {
    
    private static String test_database = "test_library";
    
    public AdminDaoTest() {
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
     * Test of makeAdmin method, of class AdminDao.
     */
    @Test
    public void testMakeAdmin() {
        System.out.println("makeAdmin");
        
        AdminDao instance = new AdminDao(test_database);
        
        //Create User
        User u = new User();
        //Set Values
        u.setUsername("test_user");
        u.setEmail("testemail@test.com");
        u.setF_name("test");
        u.setL_name("user");
        u.setDate(User.getCurrentDate());
        //Generate Password Hash
        boolean check = false;
        do {
            check = false;
            
            String salt = User.generateSalt();
            
            if(instance.checkSalt(salt)) {
                u.setSalt(salt);
                u.setPassword(User.generateSaltedHash("Password12345", salt));
            } else  {
                check = true;
            }
        } while(check);
        
        instance.addUser(u);
        
        String username = "test_user";
        boolean expResult = true;
        
        boolean result = instance.makeAdmin(username);
        assertEquals(expResult, result);
        
        instance.revertAdmin(username);
        instance.deleteUser(username);
    }

    /**
     * Test of deleteUser method, of class AdminDao.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        
        AdminDao instance = new AdminDao(test_database);
        
        //Create User
        User u = new User();
        //Set Values
        u.setUsername("test_user");
        u.setEmail("testemail@test.com");
        u.setF_name("test");
        u.setL_name("user");
        u.setDate(User.getCurrentDate());
        //Generate Password Hash
        boolean check = false;
        do {
            check = false;
            
            String salt = User.generateSalt();
            
            if(instance.checkSalt(salt)) {
                u.setSalt(salt);
                u.setPassword(User.generateSaltedHash("Password12345", salt));
            } else  {
                check = true;
            }
        } while(check);
        
        instance.addUser(u);
        
        String username = "test_user";
        boolean expResult = true;
        
        boolean result = instance.deleteUser(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of revertAdmin method, of class AdminDao.
     */
    @Test
    public void testRevertAdmin() {
        System.out.println("revertAdmin");
        
        AdminDao instance = new AdminDao(test_database);
        
        //Create User
        User u = new User();
        //Set Values
        u.setUsername("test_user");
        u.setEmail("testemail@test.com");
        u.setF_name("test");
        u.setL_name("user");
        u.setDate(User.getCurrentDate());
        //Generate Password Hash
        boolean check = false;
        do {
            check = false;
            
            String salt = User.generateSalt();
            
            if(instance.checkSalt(salt)) {
                u.setSalt(salt);
                u.setPassword(User.generateSaltedHash("Password12345", salt));
            } else  {
                check = true;
            }
        } while(check);
        
        instance.addUser(u);
        instance.makeAdmin("test_user");
        
        String username = "test_user";
        boolean expResult = true;
        
        boolean result = instance.revertAdmin(username);
        assertEquals(expResult, result);
        
        instance.deleteUser(username);
    }
    
}
