/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Title;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public interface TitleDaoInterface {
    public boolean createTitle(Title t);
    public Title getTitleById(int title_id);
    public boolean editTitle(Title t);
    public boolean deleteTitle(int title_id);
    public ArrayList<Title> searchByName(String name);
    public ArrayList<Title> searchByNameLike(String name);
    public ArrayList<Title> listAllBooks();
}
