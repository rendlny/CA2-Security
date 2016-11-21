/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
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
public class UserDaoTest {
    
    private static String test_database = "test_library";
    
    public UserDaoTest() {
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
     * Test of addUser method, of class UserDao.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        
        //Create instance of userDao
        UserDao instance = new UserDao(test_database);
        
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
        //Set expected value
        boolean expResult = true;
        //Get result
        boolean result = instance.addUser(u);
        //Compare
        assertEquals(expResult, result);
        
        AdminDao adminDao = new AdminDao(test_database);
        adminDao.deleteUser("test_user");
    }

    /**
     * Test of login method, of class UserDao.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        UserDao instance = new UserDao(test_database);
        
        
        String username = "admin";
        String password = "Password12345";
        
        boolean expResult = true;
        boolean result = false;
        
        User user = instance.login(username, password);
        
        if(user != null) {
            result = true;
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUsername method, of class UserDao.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
        
        UserDao instance = new UserDao(test_database);
        
        String username = "admin";
        boolean expResult = false;
        
        boolean result = instance.checkUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkEmail method, of class UserDao.
     */
    @Test
    public void testCheckEmail() {
        System.out.println("checkEmail");
        
        UserDao instance = new UserDao(test_database);
        
        String email = "admin@library.com";
        boolean expResult = false;
        
        boolean result = instance.checkEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkSalt method, of class UserDao.
     */
    @Test
    public void testCheckSalt() {
        System.out.println("checkSalt");
        
        UserDao instance = new UserDao(test_database);
        
        String salt = "9J6kIsveP+qFZ/c+dn+/sByb4dWd3deO/4Ac9gSFZk8=";
        boolean expResult = false;
        
        boolean result = instance.checkSalt(salt);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchUsers method, of class UserDao.
     */
    @Test
    public void testSearchUsers() {
        System.out.println("searchUsers");
        
        UserDao instance = new UserDao(test_database);
        
        String name = "admin";
        int expResult = 1;
        
        ArrayList<User> result = instance.searchUsers(name);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchUsersLike method, of class UserDao.
     */
    @Test
    public void testSearchUsersLike() {
        System.out.println("searchUsersLike");
        
        UserDao instance = new UserDao(test_database);
        
        String name = "a";
        int expResult = 1;
        
        ArrayList<User> result = instance.searchUsersLike(name);
        assertEquals(expResult, result.size());
    }
    
}
