/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ren
 */

public class Dao {
    
    private String database;
    
    /**
     * Initialises a Dao to access the specified database name
     * @param database the name of the database to be accessed
     */
    public Dao(String database) {
        this.database = database;
    }
    
    /**
     * Gets connections
     * @return a connection object
     */
    public Connection getConnection()
    {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + database;
        String username = "root";
        String password = "";
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex1) {
            System.out.println("Failed to find driver class " + ex1.getMessage());
            System.exit(1);
        } catch (SQLException ex2) {
            System.out.println("Connection failed " + ex2.getMessage());
            System.exit(2);
        }
        return con;
    }

    /**
     * closes the connection to the databases
     * @param con ia a Connection object
     */
    public void freeConnection(Connection con)
    {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
