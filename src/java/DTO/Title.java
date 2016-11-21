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
public class Title {
    
    private final int title_id;
    private String book_title;
    private String author;
    private String publisher;
    private int year;
    private int stock;
    
    public Title() {
        title_id = -1;
        book_title = null;
        author = null;
        publisher = null;
        year = -1;
        stock = -1;
    }

    public Title(int title_id, String book_title, String author, 
                String publisher, int year, int stock) 
    {
        this.title_id = title_id;
        this.book_title = book_title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.stock = stock;
    }

    public int getTitle_id() {
        return title_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.title_id;
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
        final Title other = (Title) obj;
        if (this.title_id != other.title_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Title{" + "title_id=" + title_id + ", book_title=" + book_title 
                + ", author=" + author + ", publisher=" + publisher 
                + ", year=" + year + ", stock=" + stock + '}';
    }
    
}
