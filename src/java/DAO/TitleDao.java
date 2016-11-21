package DAO;

import DTO.Title;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public class TitleDao extends Dao implements TitleDaoInterface {
    
    public TitleDao(String database) {
        super(database);
    }

    /**
     * Adds a {@code Title} Object to the database to the title table
     * 
     * @param t {@code Title} Object that will be added to the database
     * @return boolean title_created to determine if the title was added
     */
    @Override
    public boolean createTitle(Title t) {
        
        boolean title_created = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            
            String query = "INSERT INTO title(book_title, author, publisher, "
                    + "year_published, stock) VALUES(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            
            ps.setString(1, t.getBook_title());
            ps.setString(2, t.getAuthor());
            ps.setString(3, t.getPublisher());
            ps.setInt(4, t.getYear());
            ps.setInt(5, t.getStock());
            
            int result = ps.executeUpdate();
            
            if(result == 1) {
                title_created = true;
            }
            
        } catch (SQLException e) {
            System.out.println("Exception occured in the createTitle()"
                    + " method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the createTitle() method: " + e.getMessage());
            }
        }
        
        
        return title_created;
    }

    /**
     * Gets {@code Title} Object from database with matching id
     * 
     * @param title_id used to find a title with a matching id
     * @return {@code Title} Object from database
     */
    @Override
    public Title getTitleById(int title_id) {
        
        Title t = null;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = getConnection();
            
            String query = "SELECT * FROM title WHERE title_id = ?";
            
            ps = con.prepareStatement(query);
            
            ps.setInt(1, title_id);
            
            rs = ps.executeQuery();
            
            if(rs.next()) {
                t = new Title(
                        rs.getInt("title_id"),
                        rs.getString("book_title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getInt("year_published"),
                        rs.getInt("stock")
                );
            }
            
        } catch (SQLException ex) {
            System.out.println("Exception occured in the finally section of"
                        + " the getTitleById() method: " + ex.getMessage());
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the createTitle() method: " + e.getMessage());
            }
        }
        
        return t;
    }

    /**
     * Edits a title in the database using the given database
     * 
     * @param t {@code Title} Object holding the values that will be stored in
     * the database
     * @return boolean to signify whether or not a change has been made
     */
    @Override
    public boolean editTitle(Title t) {
        
        boolean edited = false;
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = getConnection();
            
            String query = "UPDATE title SET "
                    + "book_title = ?, "
                    + "author = ?, "
                    + "publisher = ?, "
                    + "year_published = ?, "
                    + "stock = ? "
                    + "WHERE title_id = ?";
            ps = con.prepareStatement(query);
            
            ps.setString(1, t.getBook_title());
            ps.setString(2, t.getAuthor());
            ps.setString(3, t.getPublisher());
            ps.setInt(4, t.getYear());
            ps.setInt(5, t.getStock());
            ps.setInt(6, t.getTitle_id());
            int affect_row = ps.executeUpdate();
            if(affect_row == 1) {
                edited = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the createTitle()"
                    + " method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the editTitle() method: " + e.getMessage());
            }
        }
        return edited;
    }

    /**
     * Deletes a record from the title table in the database with a matching id
     * 
     * @param title_id used to find the record to be removed
     * @return boolean to check if delete was successful
     */
    @Override
    public boolean deleteTitle(int title_id) {
        
        boolean deleted = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = getConnection();
            
            String query = "DELETE FROM title WHERE title_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, title_id);
            int affected_rows = ps.executeUpdate(); 
            
            if(affected_rows > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the createTitle()"
                    + " method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the createTitle() method: " + e.getMessage());
            }
        }
        return deleted;
    }

    /**
     * Searches the database for titles with an exact match on name
     * 
     * @param name of the title we wish to find
     * @return {@code ArrayList} of {@code Title} Objects with an exact match
     */
    @Override
    public ArrayList<Title> searchByName(String name) {
        
        Title t =  null;
        ArrayList<Title> titles = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = getConnection();

            String query = "SELECT * FROM title WHERE book_title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery(); 
            while(rs.next()){
                t = new Title(
                    rs.getInt("title_id"),
                    rs.getString("book_title"), 
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("year_published"),
                    rs.getInt("stock")
                );
                titles.add(t);
            }
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the searchByName() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the searchByName() method: " + e.getMessage());
            }
        }
        return titles;
    }

    /**
     * Searches the database for titles with an similar match on name
     * 
     * @param name of the title we wish to find
     * @return {@code ArrayList} of {@code Title} Objects with an similar match
     */
    @Override
    public ArrayList<Title> searchByNameLike(String name) {
        
        Title t =  null;
        ArrayList<Title> titles = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = getConnection();

            String query = "SELECT * FROM title WHERE book_title LIKE ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, ("%" + name + "%"));
            rs = ps.executeQuery(); 
            while(rs.next()){
                t = new Title(
                    rs.getInt("title_id"),
                    rs.getString("book_title"), 
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("year_published"),
                    rs.getInt("stock")
                );
                titles.add(t);
            }
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the searchByNameLike() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the searchByNameLike() method: " + e.getMessage());
            }
        }
        return titles;
    }

    /**
     * Gets a list of all the currently stored titles in the title table from
     * our database
     * 
     * @return {@code ArrayList} of all the {@code Title} Objects in the 
     * database
     */
    @Override
    public ArrayList<Title> listAllBooks() {
        
        ArrayList<Title> allBooks = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();

            String query = "SELECT * FROM title";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next()){
                Title t = new Title(
                    rs.getInt("title_id"),
                    rs.getString("book_title"), 
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("year_published"),
                    rs.getInt("stock")
                );
                allBooks.add(t);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the listAllBooks() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the listAllBooks() method: " + e.getMessage());
            }
        }
        
        return allBooks;
    }
    
}
